package com.yhw.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yhw.entity.Code;

@Repository
@Transactional
public class QuestionDao {
	@Autowired
	SessionFactory factory;
	
	public Session getSession() {
		return factory.getCurrentSession();
	}
	public QuestionDao() {
		System.out.println("questionDao 初始化。。");
	}
	public int updateResult(Integer id, boolean flag) {
		String sql = null ;
		if(flag) {
			sql = "update question set sucess = sucess + 1,total = total + 1 where ( select q_id from code where id = :id)";
		}else {
			sql = "update question set total = total + 1 where ( select q_id from code where id = :id)";
		}
		return getSession().createSQLQuery(sql).setParameter("id", id).executeUpdate();
	}
	//成功后不只是修改结果，还要对问题的成功次数加一
	public int updateResult(Integer id,Integer result,Long runTime, Long runSize) {
		StringBuffer sb = new StringBuffer();
		sb.append("update Result r set r.result = :result");
		if(runTime != null) {
			sb.append(",r.time = :time");
		}
		if(runSize != null) {
			sb.append(",r.size= :size");
		}
		sb.append(" where r.codeId = :id");
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("result", String.valueOf(result));
		query.setParameter("id", id);
		if(runTime != null) {
			query.setParameter("time", String.valueOf(runTime));
		}
		if(runSize != null) {
			query.setParameter("size",String.valueOf(runSize));
		}
		return query.executeUpdate();
	}
	public int updateResult(Integer id,Integer result, String error) {
		StringBuffer sb = new StringBuffer();
		sb.append("update Result r set r.result = :result,error=:error ");
		sb.append("where r.codeId = :id");
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("result", String.valueOf(result));
		query.setParameter("id", id);
		query.setParameter("error", error);
		return query.executeUpdate();
	}
	public int updateUser(Integer id, Integer score, String language) {
		Query q = getSession().createQuery("from Code where id = :id");
		Code c = (Code) q.setParameter("id", id).uniqueResult();
		StringBuffer sb = new StringBuffer();
		sb.append("update high_ladder hl,language l set hl.score = hl.score + :score where hl.user_Id = :id and hl.language_id = l.id and l.language = :language ");
		Query query = getSession().createSQLQuery(sb.toString());
		query.setParameter("id", c.getUserId());
		query.setParameter("score", new Double(score));
		query.setParameter("language", language);
		return query.executeUpdate();
	}
}
