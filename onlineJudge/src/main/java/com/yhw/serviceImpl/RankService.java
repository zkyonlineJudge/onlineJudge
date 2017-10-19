package com.yhw.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yhw.daoImpl.HighLadderDao;
import com.yhw.daoImpl.RankDao;
import com.yhw.entity.Rank;

@Service
@Transactional
public class RankService extends BaseService<Rank>{
	
	@Autowired
	public void setDao(RankDao dao) {
		super.setDao(dao);
	}
	public RankDao getDao() {
		return (RankDao) super.getDao();
	}
	
}
