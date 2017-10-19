package com.yhw.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yhw.daoImpl.HighLadderDao;
import com.yhw.daoImpl.HighLadderTitleDao;
import com.yhw.daoImpl.LanguageDao;
import com.yhw.entity.HighLadder;
import com.yhw.entity.HighLadderTitle;
import com.yhw.model.HighLadderModel;

@Service
@Transactional
public class HighLadderService extends BaseService<HighLadder>{
	
	@Autowired
	private HighLadderTitleDao highLadderTitleDao;
	@Autowired
	private LanguageDao languageDao;
	@Autowired
	public void setDao(HighLadderDao dao) {
		super.setDao(dao);
	}
	public HighLadderDao getDao() {
		return (HighLadderDao) super.getDao();
	}
	public List<HighLadderModel> findLimitHighLadder(Integer lid,Integer hsize){
		 return getDao().findLimitHighLadder(lid, hsize);	
	}
	public List<HighLadderModel> findLimitHighLadder(Integer hsize){
		 return getDao().findLimitHighLadder( hsize);	
	}
	/**
	 * 获取用户所有语言中最高分的记录
	 * @return
	 */
	public HighLadderModel getMaxHighLadder(Integer userId) {
		HighLadder h = getDao().getMaxHighLadder(userId);
		if(h == null) {
			return new HighLadderModel();
		}else {
			Integer position = getDao().getPosition(h.getScore(), h.getLanguageId()) + 1;
			HighLadderTitle title = highLadderTitleDao.findRecordByProperty("id", h.getTitleId());
			if(title == null) {
				return new HighLadderModel();
			}else {
				return new HighLadderModel(h, position, title.getImage(), title.getTitle(), languageDao.findRecordByProperty("id", h.getLanguageId()).getLanguage());
			}
		}
	}
}
