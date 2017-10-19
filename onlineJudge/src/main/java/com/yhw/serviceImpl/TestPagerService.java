package com.yhw.serviceImpl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.yhw.daoImpl.TestPagerDao;
import com.yhw.entity.Question;
import com.yhw.entity.TestPageQuestion;
import com.yhw.entity.TestPager;
import com.yhw.util.Constants;
import com.yhw.util.FileUtil;
@Service
@Transactional
public class TestPagerService extends BaseService<TestPager> {
	@Autowired
	QuestionService questionService;
	@Autowired
	public void setDao(TestPagerDao dao) {
		super.setDao(dao);
	}
	public TestPagerDao getDao() {
		return (TestPagerDao) super.getDao();
	}
	public TestPager createPager(Integer userId , Integer questionNum, Integer timeLimit, String rank, Map data) {
		// TODO Auto-generated method stub
		TestPager test = new TestPager();
		test.setCount(questionNum);
		test.setTimeLimit(timeLimit);
		TestPager insert = getDao().insert(test);
		List<Question> list = questionService.getDao().getQuestionByRank(rank);
		System.out.println(list.size());
		if(list != null && list.size() > 0) {
			if(list.size() < questionNum) {
				for(Question q : list) {
					insertTPQuestion(q, test);
				}
				if(list.size() > 0) {
					Question q = list.get(0);
					data.put("question", q);
					data.put("inputExam", FileUtil.getStringFromFileN(Constants.getInputPath(q.getId()) + "0.in"));
					data.put("outputExam", FileUtil.getStringFromFileN(Constants.getOutputPath(q.getId()) + "0.out"));
				}
				test.setCount(list.size());
			}else {
				//从这些题目中随机选取部分题目
				List<Question> l = getRandomQuestion(list, questionNum);
				for(Question q : l) {
					insertTPQuestion(q, test);
				}
				if(l.size() > 0) {
					Question q = l.get(0);
					data.put("question", q);
					data.put("inputExam", FileUtil.getStringFromFileN(Constants.getInputPath(q.getId()) + "0.in"));
					data.put("outputExam", FileUtil.getStringFromFileN(Constants.getOutputPath(q.getId()) + "0.out"));
				}
			}
		}
		return insert;
	}
	public void insertTPQuestion(Question q , TestPager tp) {
		TestPageQuestion tpQuestion = new TestPageQuestion();
		tpQuestion.setQid(q.getId());
		tpQuestion.setTpid(tp.getId());
		getDao().insertTPQuestion(tpQuestion);
	}
	public List<Question> getRandomQuestion(List<Question> list, int num) {
		List<Question> res = new LinkedList<>();
		for(int i = 0;i < num;i++) {
			int ran = (int)(list.size() * Math.random());
			Question q = list.get(ran);
			res.add(q);
			list.remove(ran);
		}
		return res;
	}
	public static void main(String[] args) {
		List<Question> res = new LinkedList<>();
		Question q1 = new Question();
		q1.setId(1);
		Question q2 = new Question();
		q2.setId(2);
		Question q3 = new Question();
		q3.setId(3);
		Question q4 = new Question();
		q4.setId(4);
		res.add(q1);
		res.add(q2);
		res.add(q3);
		res.add(q4);
		TestPagerService s = new TestPagerService();
		List<Question> r = s.getRandomQuestion(res, 2);
		System.out.println(JSON.toJSONString(r));
	}
	public List<Question> getQuestionList(Integer tpId) {
		// TODO Auto-generated method stub
		return  getDao().getQuestionList(tpId);
	}
	public Question getNextQuestion(Integer tpId, Integer qid) {
		// TODO Auto-generated method stub
		return getDao().getNextQuestion(tpId, qid);
	}
}
