package com.yhw.daoImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yhw.dao.BaseHibernateDao;
import com.yhw.entity.Question;
import com.yhw.model.PostModel;
import com.yhw.model.QuestionManageModel;
import com.yhw.model.QuestionModel;


@Repository
@Transactional
public class QuestionDao extends BaseHibernateDao<Question> {
	//获取题目成功率
       public List<QuestionModel> questionList(Integer countSize,Integer countPage){
    	   String hql="select new com.yhw.model.QuestionModel(q.id,q.rank,q.title,q.sucess,q.total) from Question q";
    	   Query query=getSession().createQuery(hql);
    	   query.setFirstResult((countPage-1)*countSize);
   		   query.setMaxResults(countSize);
   		   List<QuestionModel> list =query.list();
   		   return list;
    	   
       }
     //获取题目成功率
       public List<QuestionModel> questionList(){
    	   String hql="select new com.yhw.model.QuestionModel(q.id,q.rank,q.title,q.sucess,q.total) from Question q";
    	   Query query=getSession().createQuery(hql);
//    	   query.setFirstResult((countPage-1)*countSize);
//   		   query.setMaxResults(countSize);
   		   List<QuestionModel> list =query.list();
   		   return list;
    	   
       }
    //根据题目标题模糊查询
       public List<Question> searchQuestionByTitle(Question dec,String name,Integer countSize,Integer countPage){
    	   String hql="from Question q ";
    	   hql+="where 1=1";
    	   if(dec.getId()!=null && name.equals("id")){
    		   hql+=" and q.id = "+dec.getId();
    	   }
    	   if(dec.getTitle()!=null && name.equals("title")){
    		   hql+=" and q.title like '%"+dec.getTitle()+"%'";
    	   }
    	   if(dec.getRank()!=null && name.equals("rank")){
    		   hql+=" and q.rank = '"+dec.getRank()+"'";
    	   }
    	   Query query=getSession().createQuery(hql);
    	   query.setFirstResult((countPage-1)*countSize);
   		   query.setMaxResults(countSize);
   		   List<Question> list =query.list();
   		   return list;
    	   
       }
       //批量删除 
       public Integer batchDelete(List<Integer> idList){
    	   String hql="delete from Question ";
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
       //后台題目列表
       public List<QuestionManageModel> manageList(String title,String rank,String userId,Integer countSize,Integer countPage){
    	   String hql="select q.id,q.description,q.rank,q.title,q.timeLimit,q.sizeLimit,q.amountTime,q.inputDesc,q.outputDesc,q.inputPath,q.outputPath,q.source,q.userId,q.total,q.sucess,u.username from Question q,User u where q.userId=u.id";
    	   if("".equals(title)){
    		   hql+=" and q.title like'%'"+title+"'%'";
    	   }
    	   if("".equals(rank)){
    		   hql+=" and q.rank='"+rank+"'";
    	   }
    	   if("".equals(userId)){
    		   hql+=" and q.userId="+userId;
    	   }
    	   Query query = getSession().createQuery(hql);
    	   query.setFirstResult((countPage-1)*countSize);
   		   query.setMaxResults(countSize);   
   		   List<Object[]> list = query.list();
   		   List<QuestionManageModel> managelist=new ArrayList<QuestionManageModel>();
   		   for(Object o[]:list){
   			QuestionManageModel qmm =new QuestionManageModel();
   			qmm.setId((Integer)o[0]);
   			qmm.setDescription(String.valueOf(o[1]));
   			qmm.setRank(String.valueOf(o[2]));
   			qmm.setTitle(String.valueOf(o[3]));
   			qmm.setTimeLimit((Integer)o[4]);
   			qmm.setSizeLimit((Integer)o[5]);
   			qmm.setAmountTime((Integer)o[6]);
   			qmm.setInputDesc(String.valueOf(o[7]));
   			qmm.setOutputDesc(String.valueOf(o[8]));
   			qmm.setInputPath(String.valueOf(o[9]));
   			qmm.setOutputPath(String.valueOf(o[10]));
   			qmm.setSource(String.valueOf(o[11]));
   			qmm.setUserId((Integer)o[12]);
   			qmm.setTotal((Integer)o[13]);
   			qmm.setSucess((Integer)o[14]);
   			qmm.setUsername(String.valueOf(o[15]));
   			managelist.add(qmm);
  		  }
    	   return managelist;
       }
       //后台題目列表
       public List<QuestionManageModel> manageList(){
    	   String hql="select q.id,q.description,q.rank,q.title,q.timeLimit,q.sizeLimit,q.amountTime,q.inputDesc,q.outputDesc,q.inputPath,q.outputPath,q.source,q.userId,q.total,q.sucess,u.username from Question q,User u where q.userId=u.id";
    	   Query query = getSession().createQuery(hql);
   		   List<Object[]> list = query.list();
   		   List<QuestionManageModel> managelist=new ArrayList<QuestionManageModel>();
   		   for(Object o[]:list){
   			QuestionManageModel qmm =new QuestionManageModel();
   			qmm.setId((Integer)o[0]);
   			qmm.setDescription(String.valueOf(o[1]));
   			qmm.setRank(String.valueOf(o[2]));
   			qmm.setTitle(String.valueOf(o[3]));
   			qmm.setTimeLimit((Integer)o[4]);
   			qmm.setSizeLimit((Integer)o[5]);
   			qmm.setAmountTime((Integer)o[6]);
   			qmm.setInputDesc(String.valueOf(o[7]));
   			qmm.setOutputDesc(String.valueOf(o[8]));
   			qmm.setInputPath(String.valueOf(o[9]));
   			qmm.setOutputPath(String.valueOf(o[10]));
   			qmm.setSource(String.valueOf(o[11]));
   			qmm.setUserId((Integer)o[12]);
   			qmm.setTotal((Integer)o[13]);
   			qmm.setSucess((Integer)o[14]);
   			qmm.setUsername(String.valueOf(o[15]));
   			managelist.add(qmm);
  		  }
    	   return managelist;
       }
	public List<Question> selectUndoneQuestion(Integer userId, String rank) {
	   // TODO Auto-generated method stub
	   String sql="select * from question where rank =:rank and id not in (select DISTINCT code.q_id from code,result where code.user_id =:userId and code.result_id = result.id and result.result = '1')"; 
 	   Query query = getSession().createSQLQuery(sql).addEntity(Question.class).setParameter("userId", userId).setParameter("rank", rank);
	   return query.list();
	}
	public List<Question> getQuestionByRank(String rank) {
		   // TODO Auto-generated method stub
		   String sql="from Question where rank = :rank"; 
			
	 	   Query query=getSession().createQuery(sql).setString("rank", rank);
	 	   
		   return query.list();
	 	   
	}
}
