package com.yhw.daoImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yhw.dao.BaseHibernateDao;
import com.yhw.entity.Post;
import com.yhw.model.PostModel;
@Repository
@Transactional
public class PostDao extends BaseHibernateDao<Post>{
	   //根据语言id 查找 对应 公告post
       public List<PostModel> listLimit(Integer lid){
    	   String hql="select p.id,l.language,p.title,p.date,u.username,p.content from Post p,Language l,User u where p.languageId= l.id and p.authorId=u.id and p.languageId=? order by p.date desc";
    	   Query query = getSession().createQuery(hql);
    	   query.setInteger(0, lid);
    	   query.setFirstResult(0);
   		   query.setMaxResults(4);
   		   List<Object[]> list = query.list();
 		   List<PostModel> postList=new ArrayList<PostModel>();
 		  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 		   for(Object o[]:list){
 			 PostModel pm=new PostModel();
 			 pm.setId((Integer) o[0]);
 			 pm.setLanguage(String.valueOf(o[1]));
 			 pm.setTitle(String.valueOf(o[2]));
 			 pm.setDate(simpleDateFormat.format((Date)o[3]));
 			 pm.setAuth(String.valueOf(o[4]));
 			 pm.setContent(String.valueOf(o[5]));
 			 postList.add(pm);
 		  }
    	   return postList;      
       }
       //后台广告列表
       public List<PostModel> postList(String title,String languge,Integer countSize,Integer countPage){
    	   String hql="select p.id,l.language,p.title,p.date,u.username,p.content from Post p,Language l,User u where p.languageId= l.id and p.authorId=u.id";
    	   if("".equals(title)){
    		   hql+=" and p.title like'%'"+title+"'%'";
    	   }
    	   if("".equals(languge)){
    		   hql+=" and l.language='"+languge+"'";
    	   }
    	   Query query = getSession().createQuery(hql);
    	   query.setFirstResult((countPage-1)*countSize);
   		   query.setMaxResults(countSize);   
   		   List<Object[]> list = query.list();
   		   List<PostModel> postList=new ArrayList<PostModel>();
   		   SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  		   for(Object o[]:list){
  			 PostModel pm=new PostModel();
  			 pm.setId((Integer) o[0]);
  			 pm.setLanguage(String.valueOf(o[1]));
  			 pm.setTitle(String.valueOf(o[2]));
  			 pm.setDate(simpleDateFormat.format((Date)o[3]));
  			 pm.setAuth(String.valueOf(o[4]));
  			 pm.setContent(String.valueOf(o[5]));
  			 postList.add(pm);
  		  }
 	       return postList; 
       }
     //后台广告列表
       public List<PostModel> postList(){
    	   String hql="select p.id,l.language,p.title,p.date,u.username,p.content from Post p,Language l,User u where p.languageId= l.id and p.authorId=u.id";
    	   Query query = getSession().createQuery(hql);
   		   List<Object[]> list = query.list();
   		   List<PostModel> postList=new ArrayList<PostModel>();
   		   SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  		   for(Object o[]:list){
  			 PostModel pm=new PostModel();
  			 pm.setId((Integer) o[0]);
  			 pm.setLanguage(String.valueOf(o[1]));
  			 pm.setTitle(String.valueOf(o[2]));
  			 pm.setDate(simpleDateFormat.format((Date)o[3]));
  			 pm.setAuth(String.valueOf(o[4]));
  			 pm.setContent(String.valueOf(o[5]));
  			 postList.add(pm);
  		  }
 	       return postList; 
       }
     //根据公告id查公告信息
       public PostModel listBypid(Integer pid){
    	   String hql="select p.id,l.language,p.title,p.date,u.username,p.content from Post p,Language l,User u where p.languageId= l.id and p.authorId=u.id and p.id="+pid;
    	   Query query = getSession().createQuery(hql);
    	   List<Object[]> list=query.list();
    	   PostModel pm=null;
    	   if(list.size()!=0){
    		   SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      		   for(Object o[]:list){
      			 pm=new PostModel();
      			 pm.setId((Integer) o[0]);
      			 pm.setLanguage(String.valueOf(o[1]));
      			 pm.setTitle(String.valueOf(o[2]));
      			 pm.setDate(simpleDateFormat.format((Date)o[3]));
      			 pm.setAuth(String.valueOf(o[4]));
      			 pm.setContent(String.valueOf(o[5]));
      		  }
    	   }
    	   return pm;
       }
       //批量删除 
       public Integer batchDelete(List<Integer> idList){
    	   String hql="delete from Post ";
    	   hql+="where";
    	   for(int i=0;i<idList.size();i++){
    		   if(i==0){
    			   hql+=" id= "+idList.get(i);
    		   } else {
    			   hql+=" or id= "+idList.get(i);
    		   }
    	   }
    	   Query query = getSession().createQuery(hql);
		   query.executeUpdate();
    	   return 1;
       }
       
       
}
