package com.yhw.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yhw.daoImpl.LanguageDao;
import com.yhw.entity.Language;

@Service
@Transactional
public class LanguageService extends BaseService<Language> {
	@Autowired
	public void setDao(LanguageDao dao) {
		super.setDao(dao);
	}
	public LanguageDao getDao() {
		return (LanguageDao) super.getDao();
	}
}
