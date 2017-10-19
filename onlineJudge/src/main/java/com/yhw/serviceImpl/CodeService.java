package com.yhw.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yhw.daoImpl.CodeDao;
import com.yhw.entity.Code;
@Service
@Transactional
public class CodeService extends BaseService<Code> {
	@Autowired
	public void setDao(CodeDao dao) {
		super.setDao(dao);
	}
	public CodeDao getDao() {
		return (CodeDao) super.getDao();
	}
	public Integer[] getCodeNum(Integer userId, String type) {
		Integer[] arr = new Integer[2];
		arr[0] = getDao().getSuccessCodeNum(userId, type);
		arr[1] = getDao().getCodeNum(userId, type);
		return arr;
	}
	
}
