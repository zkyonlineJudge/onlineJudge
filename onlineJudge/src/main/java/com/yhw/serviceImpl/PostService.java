package com.yhw.serviceImpl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yhw.daoImpl.PostDao;
import com.yhw.entity.Post;
import com.yhw.model.PostModel;

@Service
@Transactional
public class PostService extends BaseService<Post> {
	@Autowired
	public void setDao(PostDao dao) {
		super.setDao(dao);
	}
	public PostDao getDao() {
		return (PostDao) super.getDao();
	}
	//根据语言id 查找 对应 公告post
	 public List<PostModel> listLimit(Integer lid){
		 return getDao().listLimit(lid);
		 	      
     }
	 //后台广告列表
     public List<PostModel> postList(String title,String languge,Integer countSize,Integer countPage){
    	 return getDao().postList(title, languge, countSize, countPage);
     }
   //后台广告列表
     public List<PostModel> postList(){
    	 return getDao().postList();
     }
     //根据pid查对应公告
     public PostModel listBypid(Integer pid){
    	 return getDao().listBypid(pid);
     }
     //根据id删除对应公告（可批量）
     public Integer batchDelete(List<Integer> idList){
    	 return getDao().batchDelete(idList);
     }
}
