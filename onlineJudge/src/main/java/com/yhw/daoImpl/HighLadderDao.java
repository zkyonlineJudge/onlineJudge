package com.yhw.daoImpl;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yhw.dao.BaseHibernateDao;
import com.yhw.entity.HighLadder;
import com.yhw.model.HighLadderModel;
@Repository
@Transactional
public class HighLadderDao extends BaseHibernateDao<HighLadder> {
	   //根据语言返回 hsize个排行信息
        public List<HighLadderModel> findLimitHighLadder(Integer lid,Integer hsize){
        	String hql="select new com.yhw.model.HighLadderModel(u.id,u.username,h.score,ht.title,ht.image,l.language) from HighLadder h,HighLadderTitle ht,User u,Language l where u.highLadderId=h.id and h.titleId =ht.id and h.languageId =l.id and l.id=? order by h.score desc";
        	Query query = getSession().createQuery(hql);
        	query.setInteger(0, lid);
        	query.setFirstResult(0);
    		query.setMaxResults(hsize);
    		List<HighLadderModel> list=query.list();
//    		List<HighLadderModel> listTwo =new ArrayList<HighLadderModel>();
//    		for(Object o[]:list){
//    			HighLadderModel hi = new HighLadderModel();
//    			hi.setId((Integer)o[0]);
//    			hi.setUsername(String.valueOf(o[1]));
//    			hi.setScore((Double)o[2]);
//    			hi.setTitle(String.valueOf(o[3]));
//    			hi.setImage(String.valueOf(o[4]));
//    			listTwo.add(hi);
//    		}
        	return list;
        }
      //根据语言返回 hsize个排行信息
        public List<HighLadderModel> findLimitHighLadder(Integer hsize){
        	String hql="select new com.yhw.model.HighLadderModel(u.id,u.username,h.score,ht.title,ht.image,l.language) from HighLadder h,HighLadderTitle ht,User u,Language l where u.id=h.userId and h.titleId =ht.id and l.id = h.languageId  order by h.score desc";
        	Query query = getSession().createQuery(hql);
        	query.setFirstResult(0);
    		query.setMaxResults(hsize);
    		List<HighLadderModel> list=query.list();
        	return list;
        }
        

		public HighLadder getMaxHighLadder(Integer userId) {
			// TODO Auto-generated method stub
        	String hql="from HighLadder h where h.userId = :userId order by h.score desc";
        	Query query = getSession().createQuery(hql);
        	List<HighLadder> list =  (List<HighLadder>) query.setParameter("userId", userId).list();
        	if(list != null && list.size() > 0) {
        		return list.get(0);
        	}
        	return null;
		}

		public Integer getPosition(Double score, Integer language) {
			// TODO Auto-generated method stub
        	String hql="select count(h.id) from HighLadder h where h.score > :score and languageId=:lid";
        	Query query = getSession().createQuery(hql);
        	List list = query.setParameter("score", score)
        			.setParameter("lid", language).list();
        	if(list != null && list.size() > 0){
        		return ((Long)list.get(0)).intValue();
        	}else {
        		return -1;
        	}
		}
}
