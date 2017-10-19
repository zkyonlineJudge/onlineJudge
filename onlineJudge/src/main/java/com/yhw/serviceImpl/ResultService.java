package com.yhw.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yhw.daoImpl.ResultDao;
import com.yhw.entity.Result;
import com.yhw.model.ResultModel;
@Service
@Transactional
public class ResultService extends BaseService<Result> {
	@Autowired
	public void setDao(ResultDao dao) {
		super.setDao(dao);
	}
	public ResultDao getDao() {
		return (ResultDao) super.getDao();
	}
	public List<ResultModel> resultList(Integer countSize,Integer countPage){
		return getDao().resultList(countSize, countPage);
	}
	public List<ResultModel> resultList(){
		return getDao().resultList();
	}
}
