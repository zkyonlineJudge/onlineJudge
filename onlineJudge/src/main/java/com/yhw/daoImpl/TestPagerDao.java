package com.yhw.daoImpl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yhw.dao.BaseHibernateDao;
import com.yhw.entity.Question;
import com.yhw.entity.TestPageQuestion;
import com.yhw.entity.TestPager;
@Repository
@Transactional
public class TestPagerDao extends BaseHibernateDao<TestPager> {

	
	
	public TestPageQuestion insertTPQuestion(TestPageQuestion tpQuestion) {
		// TODO Auto-generated method stub
		getSession().persist(tpQuestion);
		return tpQuestion;
	}

	public List<Question> getQuestionList(Integer tpId) {
		String hql = "select q from Question q,TestPageQuestion tp where tp.tpId = :tpId and tp.qid = q.id";
		List<Question> list = getSession().createQuery(hql).setInteger("tpId", tpId).list();
		if(list != null && list.size() > 0) {
			return list;
		}else {
			return new LinkedList();
		}
	}

	public Question getNextQuestion(Integer tpId, Integer qid) {
		String sql = "select  q.* from test_pager_question tpq,question q where tpq.tpid = :tpId and tpq.qid > :qid and q.id = tpq.qid order by tpq.qid asc limit 1,1";
		List<Question> list = getSession().createSQLQuery(sql).addEntity(Question.class).setInteger("tpId", tpId).setInteger("qid", qid).list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	   
}
