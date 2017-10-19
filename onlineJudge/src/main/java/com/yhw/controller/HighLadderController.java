package com.yhw.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.yhw.entity.Code;
import com.yhw.entity.Language;
import com.yhw.entity.Question;
import com.yhw.entity.Rank;
import com.yhw.entity.Result;
import com.yhw.entity.User;
import com.yhw.model.HighLadderModel;
import com.yhw.model.QuestionDTO;
import com.yhw.model.QuestionModel;
import com.yhw.serviceImpl.CodeService;
import com.yhw.serviceImpl.HighLadderService;
import com.yhw.serviceImpl.LanguageService;
import com.yhw.serviceImpl.QuestionService;
import com.yhw.serviceImpl.RankService;
import com.yhw.serviceImpl.ResultService;
import com.yhw.util.Constants;
import com.yhw.util.ErrorCodes;
import com.yhw.util.FileUtil;
import com.yhw.util.SendMessageUtil;

@Controller
public class HighLadderController {
	@Autowired
	HighLadderService highLadderService;
	@Autowired
	QuestionService questionService;
	@Autowired
	LanguageService languageService;
	@Autowired
	CodeService codeService;
	@Autowired
	RankService rankService;
	@Autowired
	ResultService resultService;

	@RequestMapping("highLadder/listlimit")
	@ResponseBody
	public Map<String, Object> listlimit(Integer lid, Integer hsize) {
		List<HighLadderModel> list = highLadderService.findLimitHighLadder(lid, hsize);
		Map<String, Object> map = new HashMap<String, Object>();
		if (null == list || list.size() == 0) {
			System.out.println(list);
			map.put("errorCode", 1);
			map.put("msg", "失败");
		} else {
			map.put("errorCode", 0);
			map.put("list", list);
			map.put("msg", "成功");
		}
		return map;
	}
	
	@RequestMapping("highLadder/nolang/listlimit")
	@ResponseBody
	public Map<String, Object> listlimitWithoutLanguage(Integer hsize) {
		List<HighLadderModel> list = highLadderService.findLimitHighLadder(hsize);
		Map<String, Object> map = new HashMap<String, Object>();
		if (null == list || list.size() == 0) {
			System.out.println(list);
			map.put("errorCode", 1);
			map.put("msg", "失败");
		} else {
			map.put("errorCode", 0);
			map.put("list", list);
			map.put("msg", "成功");
		}
		return map;
	}

	/**
	 * 提交代码
	 * 
	 * @param code
	 * @param language
	 * @param qid
	 * @param session
	 * @return
	 */
	@RequestMapping("highLadder/code/commit")
	@ResponseBody
	public Map<String, Object> commitCode(String code, String language, Integer qid, HttpSession session) {
		System.out.println("language" + language);
		Map<String, Object> map = new HashMap<String, Object>();
		User user = (User) session.getAttribute("user");
		Question q = questionService.findRecordByProperty("id", qid);
		//前端显示的语言和后台数据库不一致 需要转化
		language = Constants.langMap.get(language.toLowerCase());
		Language lang = languageService.findRecordByProperty("language", language);
		Code c = new Code();
		c.setContent(code);
		c.setLanguageId(lang.getId());
		c.setBeginTime(new Date());
		c.setQid(qid);
		c.setType(Code.TYPE_LADDER);
		c.setUserId(user.getId());
		Code insert = codeService.insert(c);
		Result result = new Result();
		result.setCodeId(insert.getId());
		result.setResult("0");
		resultService.insert(result);
		c.setResultId(result.getId());
		codeService.update(c);
		QuestionDTO dto = new QuestionDTO(c, language, q);
		SendMessageUtil.send(dto);
		map.put("errorCode", 0);
		map.put("codeId", insert.getId());
		return map;
	}

	/**
	 * 请求代码运行情况
	 * 
	 * @param codeId
	 * @param session
	 * @return
	 */
	@RequestMapping("highLadder/code/query")
	@ResponseBody
	public Map<String, Object> queryCode(Integer codeId, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		Result res = resultService.findRecordByProperty("codeId", codeId);
		Code code = codeService.findRecordByProperty("id", codeId);
		map.put("errorCode", 0);
		map.put("status", res.getResult());
		map.put("code", code.getContent());
		map.put("time", res.getTime());
		map.put("size", res.getSize());
		map.put("codeId", code.getId());
		map.put("error", res.getError());
		return map;
	}

	/**
	 * 获取下一题的内容
	 * 
	 * @param codeId
	 * @param session
	 * @return
	 */
	@RequestMapping("highLadder/next/question")
	@ResponseBody
	public Map<String, Object> getNextQuestion(HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		User user = (User) session.getAttribute("user");
		Integer userId = user.getId();
		Rank rank = rankService.findRecordByProperty("id", user.getRankId());
		Question question = questionService.getHighQuestionByRank(userId, rank.getRank());
		if (question == null) {
			map.put("errorCode", ErrorCodes.QUESTION_EMPTY);
		} else {
			QuestionModel dto = new QuestionModel(question);
			dto.setInputContent(FileUtil.getStringFromFileN(Constants.getInputPath(dto.getId()) + "0.in" ));
			dto.setOutputContent(FileUtil.getStringFromFileN(Constants.getOutputPath(dto.getId()) + "0.out" ));
			map.put("question", dto);
			map.put("errorCode", ErrorCodes.SUCCESS);
		}
		System.out.println("data" +JSON.toJSONString(map));
		return map;
	}
}
