package com.yhw.daoImpl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yhw.dao.BaseHibernateDao;
import com.yhw.entity.Comment;

@Repository
@Transactional
public class CommentDao extends BaseHibernateDao<Comment>{
	
}
