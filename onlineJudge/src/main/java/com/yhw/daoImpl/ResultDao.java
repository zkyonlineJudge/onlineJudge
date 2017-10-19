package com.yhw.daoImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yhw.dao.BaseHibernateDao;
import com.yhw.entity.Result;
import com.yhw.model.ResultModel;

@Repository
@Transactional
public class ResultDao extends BaseHibernateDao<Result>{
    public List<ResultModel> resultList(Integer countSize,Integer countPage){
    	 String hql="select c.id,u.username,q.title,r.result,r.time,r.size,l.language,c.date,q.id,u.id,r.error from Question q,Code c,Result r,User u,Language l where q.id=c.qid and c.id=r.codeId and c.userId = u.id and l.id=c.languageId order by c.date desc";
    	 Query query = getSession().createQuery(hql);
    	 query.setFirstResult((countPage-1)*countSize);
  		 query.setMaxResults(countSize);
  		 List<Object[]> list=query.list();
  		 List<ResultModel> resultList=new ArrayList<ResultModel>();
  		  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  		 for(Object o[]:list){
  			ResultModel re = new ResultModel();
  			re.setCid((Integer)o[0]);
  			re.setUsername(String.valueOf(o[1]));
  			re.setTitle(String.valueOf(o[2]));
  			re.setResult(String.valueOf(o[3]));
  			re.setTime(String.valueOf(o[4]));
  			re.setSize(String.valueOf(o[5]));
  			re.setLanguage(String.valueOf(o[6]));
  			re.setDate(simpleDateFormat.format((Date)o[7]));
  			re.setQid((Integer)o[8]);
  			re.setUid((Integer)o[9]);
  			re.setError(String.valueOf(o[10]));
  			resultList.add(re);
  		 }
    	 return resultList;
    }
    public List<ResultModel> resultList(){
   	 String hql="select c.id,u.username,q.title,r.result,r.time,r.size,l.language,c.date,q.id,u.id,r.error from Question q,Code c,Result r,User u,Language l where q.id=c.qid and c.id=r.codeId and c.userId = u.id and l.id=c.languageId order by c.date desc";
   	 Query query = getSession().createQuery(hql);
//   	 query.setFirstResult((countPage-1)*countSize);
// 		 query.setMaxResults(countSize);
 		 List<Object[]> list=query.list();
 		 List<ResultModel> resultList=new ArrayList<ResultModel>();
 		  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 		 for(Object o[]:list){
 			ResultModel re = new ResultModel();
 			re.setCid((Integer)o[0]);
 			re.setUsername(String.valueOf(o[1]));
 			re.setTitle(String.valueOf(o[2]));
 			re.setResult(String.valueOf(o[3]));
 			re.setTime(String.valueOf(o[4]));
 			re.setSize(String.valueOf(o[5]));
 			re.setLanguage(String.valueOf(o[6]));
 			re.setDate(simpleDateFormat.format((Date)o[7]));
 			re.setQid((Integer)o[8]);
 			re.setUid((Integer)o[9]);
 			re.setError(String.valueOf(o[10]));
 			resultList.add(re);
 		 }
   	 return resultList;
   }
}
