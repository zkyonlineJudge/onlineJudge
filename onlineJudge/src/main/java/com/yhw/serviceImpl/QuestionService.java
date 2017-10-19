package com.yhw.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yhw.daoImpl.QuestionDao;
import com.yhw.daoImpl.UserDao;
import com.yhw.entity.Question;
import com.yhw.model.QuestionManageModel;
import com.yhw.model.QuestionModel;
@Service
@Transactional
public class QuestionService extends BaseService<Question>{
	@Autowired
	public void setDao(QuestionDao dao) {
		super.setDao(dao);
	}
	public QuestionDao getDao() {
		return (QuestionDao) super.getDao();
	}
	public List<QuestionModel> questionList(Integer countSize,Integer countPage){
		return getDao().questionList( countSize, countPage);	
	}
	public List<QuestionModel> questionList(){
		return getDao().questionList();	
	}
	  public List<Question> searchQuestionByTitle(Question dec,String name,Integer countSize,Integer countPage){
		  return getDao().searchQuestionByTitle(dec,name,countSize, countPage);
	  }
	//批量删除 
      public Integer batchDelete(List<Integer> idList){
    	  return getDao().batchDelete(idList);
      }
     //后台题目列表
      public List<QuestionManageModel> manageList(String title,String rank,String userId,
    		  Integer countSize,Integer countPage){
    	  return getDao().manageList(title, rank, userId, countSize, countPage);
    	  
      }
    //后台题目列表
      public List<QuestionManageModel> manageList(){
    	  return getDao().manageList();
    	  
      }
	public Question getHighQuestionByRank(Integer userId, String rank) {
		// TODO Auto-generated method stub
		List<Question> list = getDao().selectUndoneQuestion(userId, rank);
		if(list.size() > 0) {
			int pos = (int)(list.size() * Math.random());
			return list.get(pos);
		}else {
			return null;
		}
	}
	public static void main(String[] args) {
		int pos = (int)(10 * Math.random());
		System.out.println(pos);
	}
}
