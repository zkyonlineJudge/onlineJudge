package com.yhw.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yhw.entity.Question;
import com.yhw.entity.TestPager;
import com.yhw.entity.User;
import com.yhw.serviceImpl.AuthService;
import com.yhw.serviceImpl.CodeService;
import com.yhw.serviceImpl.HighLadderService;
import com.yhw.serviceImpl.QuestionService;
import com.yhw.serviceImpl.TestPagerService;
import com.yhw.serviceImpl.UserService;
import com.yhw.util.Constants;
import com.yhw.util.ErrorCodes;
import com.yhw.util.FileUtil;

@Controller
@RequestMapping(value = "/testpager")
public class TestPagerController {
	@Autowired
	private UserService userService;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private CodeService codeService;
	@Autowired
	private HighLadderService highLadderService;
	@Autowired
	private AuthService authService;
	@Autowired
	private TestPagerService testPagerService;
	@RequestMapping("/get")
	public @ResponseBody Map getTestPager(HttpSession session, Integer questionNum, Integer TimeLimit, String rank) {
		Integer errorCode = ErrorCodes.SUCCESS;
		Map retMap = new HashMap();
		User user = (User) session.getAttribute("user");
		TestPager tp = testPagerService.createPager(user.getId(), questionNum, TimeLimit, rank, retMap);
		retMap.put("errorCode", errorCode);
		retMap.put("testPager", tp);
		return retMap;
	}
	@RequestMapping("/list/get")
	public @ResponseBody Map getQuestionList(HttpSession session, Integer tpId) {
		Integer errorCode = ErrorCodes.SUCCESS;
		Map retMap = new HashMap();
		User user = (User) session.getAttribute("user");
		List<Question> list = testPagerService.getQuestionList(tpId);
		retMap.put("errorCode", errorCode);
		retMap.put("list", list);
		return retMap;
	}
	/**
	 * 获取用户的所有试卷
	 * @param session
	 * @param tpId
	 * @return
	 */
	@RequestMapping("/user/getpager")
	public @ResponseBody Map getTPList(HttpSession session) {
		Integer errorCode = ErrorCodes.SUCCESS;
		Map retMap = new HashMap();
		User user = (User) session.getAttribute("user");
		List<TestPager> list = testPagerService.findRecordsByProperty("userId", user.getId());
		
		retMap.put("errorCode", errorCode);
		retMap.put("list", list);
		return retMap;
	}
	@RequestMapping("/next/question")
	public @ResponseBody Map getNextQuestion(HttpSession session, Integer qid, Integer tpId) {
		Integer errorCode = ErrorCodes.SUCCESS;
		Map retMap = new HashMap();
		User user = (User) session.getAttribute("user");
		Question question = testPagerService.getNextQuestion(tpId, qid);
		retMap.put("errorCode", errorCode);
		retMap.put("question", question);

		retMap.put("inputExam", FileUtil.getStringFromFileN(Constants.getInputPath(question.getId()) + "0.in"));
		retMap.put("outputExam", FileUtil.getStringFromFileN(Constants.getOutputPath(question.getId()) + "0.out"));
		return retMap;
	}
}
