package com.yhw.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yhw.daoImpl.CommentDao;
import com.yhw.entity.Comment;
@Service
@Transactional
public class CommentService extends BaseService<Comment> {
	@Autowired
	public void setDao(CommentDao dao) {
		super.setDao(dao);
	}
	public CommentDao getDao() {
		return (CommentDao) super.getDao();
	}
	
}
