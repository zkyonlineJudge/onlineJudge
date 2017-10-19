package com.yhw.serviceImpl;

import java.math.BigInteger;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yhw.daoImpl.CodeDao;
import com.yhw.daoImpl.CommentDao;
import com.yhw.daoImpl.QuestionDao;
import com.yhw.daoImpl.UserDao;
import com.yhw.entity.Code;
import com.yhw.entity.Comment;
import com.yhw.entity.Question;
import com.yhw.entity.User;
import com.yhw.model.NynamicModel;
import com.yhw.util.ErrorCodes;
@Service
@Transactional
public class UserService extends BaseService<User>{
	@Autowired
	private QuestionDao questionDao;
	@Autowired
	private CodeDao codeDao;
	@Autowired
	private CommentDao commentDao;
	@Autowired
	public void setDao(UserDao dao) {
		super.setDao(dao);
	}
	public UserDao getDao() {
		return (UserDao) super.getDao();
	}
	/**
	 * 暂时先实现我评论别人的显示 别人评论自己不显示在个人主页
	 * @param userId
	 * @return
	 */
	public Integer getRecentInf(Map retMap, Integer userId, Integer type){
		Integer errorCode = ErrorCodes.SUCCESS;
		List<NynamicModel> l = new LinkedList();
		List<Object[]> list = null;
		if(type.equals(1)) {
			list = getDao().getRecentInf(userId);
		}else if(new Integer(2).equals(type)){
			list = getDao().getRecentQuestionInf(userId);
		}else {
			list = getDao().getRecentCommentInf(userId);
		}
		for(Object[] arr : list) {
			String urlA = null;
			String urlB = null;
			String title = null;
			String content = null;
			Integer id = (Integer) arr[0];
			Integer type_id = (Integer) arr[1];
			Date time = (Date) arr[2];
			Integer flag = ((BigInteger) arr[3]).intValue();
			if(new Integer(1).equals(flag)) {
				//评论 分两种 看type
				Comment cmt = commentDao.findRecordByProperty("id",id);
				
				if(Comment.TYPE_CODE.equals(cmt.getType())){
					//代码的评论
					Code code = codeDao.findRecordByProperty("id", cmt.getTypeId());
					User user = getDao().findRecordByProperty("id", userId);
					 Question question = questionDao.findRecordByProperty("id", code.getQid());
					 if(cmt.getParentId() != 0) {
						 Comment pcmt = commentDao.findRecordByProperty("id", cmt.getParentId());
						 User pUser = getDao().findRecordByProperty("id", pcmt.getUserId());
						 // xxx 回复 xxx ：
						 content = user.getUsername() + "回复" +  pUser.getUsername() + ":";
					 }else {
						 // xxx 评论了：
						 content = user.getUsername() + "评论了:";
					 }
					 title = "RE :" + question.getTitle();
					//构建指向代码评论页面url
					urlA = "comment/code?id=" + cmt.getTypeId();
					content += cmt.getContent();
				}else {
					//问题的评论
					urlA = "comment/question?id=" + cmt.getTypeId();
					title = "RE :" + questionDao.findRecordByProperty("id", type_id).getTitle();
					content = cmt.getContent();
				}
				
			}else {
				//代码
				Question question = questionDao.findRecordByProperty("id", type_id);
				Code code = codeDao.findRecordByProperty("id", id);
				title = "题目 " + question.getTitle();
				content = question.getDescription();
				urlA = "question/detail?id=" + question.getId();
				urlB = "comment/code?id=" + code.getId();
			}
			NynamicModel  model = new NynamicModel();
			model.setContent(content);
			model.setTime(time);
			model.setUrlA(urlA);
			model.setUrlB(urlB);
			model.setTitle(title);
			l.add(model);
		}
		retMap.put("list", l);
		return errorCode;
	}
}
