package com.yhw.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yhw.daoImpl.AuthDao;
import com.yhw.entity.Auth;
@Service
@Transactional
public class AuthService extends BaseService<Auth> {
	@Autowired
	public void setDao(AuthDao dao) {
		super.setDao(dao);
	}
	public AuthDao getDao() {
		return (AuthDao) super.getDao();
	}
	
}
