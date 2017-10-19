package com.yhw.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yhw.entity.Code;
import com.yhw.entity.Language;
import com.yhw.entity.Question;
import com.yhw.entity.Result;
import com.yhw.entity.User;
import com.yhw.model.QuestionDTO;
import com.yhw.model.QuestionManageModel;
import com.yhw.model.QuestionModel;
import com.yhw.serviceImpl.CodeService;
import com.yhw.serviceImpl.LanguageService;
import com.yhw.serviceImpl.QuestionService;
import com.yhw.serviceImpl.RankService;
import com.yhw.serviceImpl.ResultService;
import com.yhw.util.SendMessageUtil;

@Controller
public class QuestionController {
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
	@RequestMapping(value = "/question/detaillist")
	@ResponseBody
	//获取根据id获取题目详情
	public Map<String,Object> detailList(Integer qid){
		Question q =  questionService.findRecordByProperty("id", qid);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("errorCode", 0);
		map.put("question", q);
		map.put("msg", "成功");
		return map; 
	}
	@RequestMapping(value = "/question/questionlist")
	@ResponseBody
	//获取题目通过率
	public Map<String,Object> questionList(Integer countSize,Integer countPage){
		List<QuestionModel> list = questionService.questionList(countSize, countPage);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("errorCode", 0);
		map.put("list", list);
		map.put("msg", "成功");
		return map; 	
	}
	@RequestMapping(value = "/question/nopager/questionlist")
	@ResponseBody
	//获取题目通过率
	public Map<String,Object> questionList(){
		List<QuestionModel> list = questionService.questionList();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("errorCode", 0);
		map.put("list", list);
		map.put("msg", "成功");
		return map; 	
	}
	@RequestMapping(value = "/question/questionsearch")
	@ResponseBody
	//name为名称 dec为模糊查询的条件
	public Map<String,Object> questionSearch(Question dec,String name,Integer countSize,Integer countPage){
		List<Question> list=new ArrayList<Question>();
		Map<String,Object> map = new HashMap<String,Object>();
			list=questionService.searchQuestionByTitle(dec,name,countSize, countPage);
			map.put("errorCode", 0);
			map.put("list", list);
			map.put("msg", "成功");
			return map;
		
	}
	//后台新增题目
	@RequestMapping(value = "/question/save")
	@ResponseBody 
	public Map<String,Object> questionSave(
			@RequestParam(value="description",required=false) String description,
			@RequestParam(value="rank",required=false) String rank,
			@RequestParam(value="title",required=false) String title,
			@RequestParam(value="timeLimit",required=false) String timeLimit,
			@RequestParam(value="sizeLimit",required=false) String sizeLimit,
			@RequestParam(value="amountTime",required=false) String amountTime,
			@RequestParam(value="inputDesc",required=false) String inputDesc,
			@RequestParam(value="outputDesc",required=false) String outputDesc,
			//@RequestParam(value="inputPath",required=false) MultipartFile inputPath,
			//@RequestParam(value="outputPath",required=false) MultipartFile outputPath,
			@RequestParam(value="source",required=false) String source,
			@RequestParam(value="total",required=false) String total,
			@RequestParam(value="sucess",required=false) String sucess,HttpServletRequest request){		
		User user = (User) request.getSession().getAttribute("user");
		Question question=new Question();
		question.setDescription(description);
		question.setRank(rank);
		question.setTitle(title);
		question.setTimeLimit(Integer.valueOf(timeLimit));
		question.setSizeLimit(Integer.valueOf(sizeLimit));
		question.setAmountTime(Integer.valueOf(amountTime));
		question.setInputDesc(inputDesc);
		question.setOutputDesc(outputDesc);
		question.setSource(source);
		question.setTotal(Integer.valueOf(total));
		question.setSucess(Integer.valueOf(sucess));
		question.setUserId(user.getId());//从session中获取 
		//question.setInputPath(FileUp.Save(inputPath, request));
		//question.setOutputPath(FileUp.Save(outputPath, request));
		Question q=questionService.insert(question);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list",q);
		map.put("errorCode", 0);
		map.put("msg", "成功");
		return map;
	} 
	//后台题目更新
	@RequestMapping(value = "/question/update")
	@ResponseBody
	public Map<String, Object> questionUpdate(@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "rank", required = false) String rank,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "timeLimit", required = false) String timeLimit,
			@RequestParam(value = "sizeLimit", required = false) String sizeLimit,
			@RequestParam(value = "amountTime", required = false) String amountTime,
			@RequestParam(value = "inputDesc", required = false) String inputDesc,
			@RequestParam(value = "outputDesc", required = false) String outputDesc,
			// @RequestParam(value="inputPath",required=false) MultipartFile inputPath,
			// @RequestParam(value="outputPath",required=false) MultipartFile outputPath,
			@RequestParam(value = "source", required = false) String source,
			@RequestParam(value = "total", required = false) String total,
			@RequestParam(value = "sucess", required = false) String sucess, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		Question question = new Question();
		question.setId(Integer.valueOf(id));
		question.setDescription(description);
		question.setRank(rank);
		question.setTitle(title);
		question.setTimeLimit(Integer.valueOf(timeLimit));
		question.setSizeLimit(Integer.valueOf(sizeLimit));
		question.setAmountTime(Integer.valueOf(amountTime));
		question.setInputDesc(inputDesc);
		question.setOutputDesc(outputDesc);
		question.setSource(source);
		question.setTotal(Integer.valueOf(total));
		question.setSucess(Integer.valueOf(sucess));
		question.setUserId(user.getId());// 从session中获取
		// question.setInputPath(FileUp.Save(inputPath, request));
		// question.setOutputPath(FileUp.Save(outputPath, request));
		Question q = questionService.update(question);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", q);
		map.put("errorCode", 0);
		map.put("msg", "成功");
		return map;
	}

	// 单一删除
	@RequestMapping(value = "/question/deletebyid")
	@ResponseBody
	public Map<String, Object> deleteById(Question question) {
		Map<String, Object> map = new HashMap<String, Object>();
		Question q = questionService.delete(question);
		map.put("list", q);
		map.put("errorCode", 0);
		map.put("msg", "成功");
		return map;
	}

	// 批量删除
	@RequestMapping(value = "/question/batchdelete")
	@ResponseBody
	public Map<String, Object> batchDelete(String ids) {
		String[] id = ids.split(",");
		List<Integer> idList = new ArrayList<Integer>();
		for (int i = 0; i < id.length; i++) {
			int a = new Integer(id[i]);
			idList.add(a);
		}
		Integer integer = questionService.batchDelete(idList);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("errorCode", 0);
		map.put("msg", "成功");
		return map;
	}

	// 题目后台列表显示
	@RequestMapping(value = "/question/managelist")
	@ResponseBody
	public Map<String, Object> manageList(String title, String rank, String userId, Integer countSize,
			Integer countPage) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<QuestionManageModel> list = new ArrayList<QuestionManageModel>();
		list = questionService.manageList(title, rank, userId, countSize, countPage);
		map.put("errorCode", 0);
		map.put("list", list);
		map.put("msg", "成功");
		return map;
	}

	// 题目后台列表显示
	@RequestMapping(value = "/question/all/managelist")
	@ResponseBody
	public Map<String, Object> manageList() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<QuestionManageModel> list = new ArrayList<QuestionManageModel>();
		list = questionService.manageList();
		map.put("errorCode", 0);
		map.put("list", list);
		map.put("msg", "成功");
		return map;
	}

	// 练习模式代码提交 
	@RequestMapping(value = "/question/commit/code")
	@ResponseBody
	public Map<String, Object> commitCode(String code, String language, Integer qid,HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		User user = (User) session.getAttribute("user");
		Question q = questionService.findRecordByProperty("id", qid);
		Language lang = languageService.findRecordByProperty("language", language);
		Code c = new Code();
		c.setContent(code);
		c.setLanguageId(lang.getId());
		c.setBeginTime(new Date());
		c.setQid(qid);
		c.setType(Code.TYPE_EXERCISE);
		c.setUserId(user.getId());
		Code insert = codeService.insert(c);
		Result result = new Result();
		result.setCodeId(insert.getId());
		resultService.insert(result);
		QuestionDTO dto = new QuestionDTO(c, language, q);
		SendMessageUtil.send(dto);
		map.put("errorCode", 0);
		map.put("codeId", insert.getId());
		return map;
	}
}
