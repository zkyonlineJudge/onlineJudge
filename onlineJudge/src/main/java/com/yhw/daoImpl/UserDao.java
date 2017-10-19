package com.yhw.daoImpl;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.yhw.dao.BaseHibernateDao;
import com.yhw.entity.User;
@Repository
public class UserDao extends BaseHibernateDao<User>{
	/**
	 * 获取最新的用户动态 包含用户的做题动态，评论动态
	 * 返回的对象有id type_id date flag flag 为1 时为评论 2时为代码
	 * 
	 */
	public List<Object[]> getRecentInf(Integer userId){
		String sql = "select cmt.id,cmt.type_id,cmt.date,1 flag from comment cmt where cmt.user_id= :userId union select c.id,c.q_id,c.date,2 from code c"  
				+ " where c.user_id= :userId order by date desc";
		Query query = getSession().createSQLQuery(sql).setParameter("userId", userId);
		query.setFirstResult(0);
		query.setMaxResults(4);
		List list = query.list();
		if(list != null && list.size() > 0) {
			return list;
		}else {
			return new LinkedList();
		}
	}
	/**
	 * 获取最新的用户动态 包含用户的做题动态
	 * 返回的对象有id type_id date flag flag 为1 时为评论 2时为代码
	 * 
	 */
	public List<Object[]> getRecentQuestionInf(Integer userId){
		String sql = "select c.id,c.q_id,c.date,2 from code c"  
				+ " where c.user_id= :userId order by date desc";
		Query query = getSession().createSQLQuery(sql).setParameter("userId", userId);
		query.setFirstResult(0);
		query.setMaxResults(4);
		List list = query.list();
		if(list != null && list.size() > 0) {
			return list;
		}else {
			return new LinkedList();
		}
	}
	/**
	 * 获取最新的用户动态 包含用户的评论动态
	 * 返回的对象有id type_id date flag flag 为1 时为评论 2时为代码
	 * 
	 */
	public List<Object[]> getRecentCommentInf(Integer userId){
		String sql = "select cmt.id,cmt.type_id,cmt.date,1 flag from comment cmt where cmt.user_id= :userId"  
				+ " order by date desc";
		Query query = getSession().createSQLQuery(sql).setParameter("userId", userId);
		query.setFirstResult(0);
		query.setMaxResults(4);
		List list = query.list();
		if(list != null && list.size() > 0) {
			return list;
		}else {
			return new LinkedList();
		}
	}
}
