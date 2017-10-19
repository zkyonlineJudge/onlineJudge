package com.yhw.daoImpl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yhw.dao.BaseHibernateDao;
import com.yhw.entity.Rank;

@Repository
@Transactional
public class RankDao extends BaseHibernateDao<Rank>{
	
}
