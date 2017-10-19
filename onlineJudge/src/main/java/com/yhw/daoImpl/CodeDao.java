package com.yhw.daoImpl;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yhw.dao.BaseHibernateDao;
import com.yhw.entity.Code;
import com.yhw.entity.Result;
import com.yhw.model.QuestionModel;
import com.yhw.model.ResultModel;

@Repository
@Transactional
public class CodeDao extends BaseHibernateDao<Code>{
	/**
	 * 获取到对应用户对应类型的做题量
	 * @param userId
	 * @return
	 */
	public Integer getCodeNum(Integer userId, String type) {
		String hql = "select count(c.id) from User u,Code c where u.id = :id and"
				+ " c.userId = :id and c.type = :type";
		Query query=getSession().createQuery(hql);
		List list = query.setParameter("id", userId)
				.setParameter("type", type).list();
		if(list != null && list.size() > 0 ) {
			return ((Long)list.get(0)).intValue();
		}else {
			return 0;
		}
	}
	/**
	 * 获取到对应用户对应类型的正确题目量
	 * @param userId
	 * @return
	 */
	public Integer getSuccessCodeNum(Integer userId, String type) {
		String hql = "select count(r.id) from User u,Code c,Result r where u.id = :id and"
				+ " c.userId = :id and c.type = :type and r.codeId = c.id and r.result =:result";
		Query query=getSession().createQuery(hql);
		List list = query.setParameter("id", userId)
				.setParameter("type", type)
				.setParameter("result", Result.ACCEPTED + "")
				.list();
		if(list != null && list.size() > 0 ) {
			return ((Long)list.get(0)).intValue();
		}else {
			return 0;
		}
	}
}
