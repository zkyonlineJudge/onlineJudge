package com.yhw.daoImpl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yhw.dao.BaseHibernateDao;
import com.yhw.entity.Auth;

@Repository
@Transactional
public class AuthDao extends BaseHibernateDao<Auth>{
	
}
