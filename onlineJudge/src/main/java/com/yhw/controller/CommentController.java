package com.yhw.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.yhw.entity.Code;
import com.yhw.entity.Comment;
import com.yhw.entity.Question;
import com.yhw.entity.User;
import com.yhw.model.CommentModel;
import com.yhw.model.HighLadderModel;
import com.yhw.serviceImpl.CodeService;
import com.yhw.serviceImpl.CommentService;
import com.yhw.serviceImpl.HighLadderService;
import com.yhw.serviceImpl.QuestionService;
import com.yhw.serviceImpl.UserService;
import com.yhw.util.Constants;
import com.yhw.util.DateUtil;
import com.yhw.util.ErrorCodes;
import com.yhw.util.FileUtil;

@Controller
public class CommentController {
     @Autowired
     QuestionService questionService;
     @Autowired
     HighLadderService highLadderService;
     @Autowired
     CodeService codeService;
     @Autowired
     CommentService commentService;
     @Autowired
     UserService userService;
     private Question q;
     
     private Code code;
     
     private List<Comment> list;
     
     /**
      * 跳转到代码评论页面
      * @return
      */
     @RequestMapping("/comment/code")
     public @ResponseBody Map toCodePage(Integer id){
    	 Integer errorCode = ErrorCodes.SUCCESS;
    	 Map map = new HashMap();
    	 code = codeService.findRecordByProperty("id", id);
    	 User author = userService.findRecordByProperty("id", code.getUserId());
    	 if(code != null) {
    		 list = commentService.findRecordsByHql("from Comment where type = ? and typeId = ? order by date desc ", Comment.TYPE_CODE , id);
    		 
    	 }
    	 map.put("author", author);
    	 map.put("code", code);
    	 map.put("errorCode", errorCode);
    	 map.put("list", list);
 		 return map; 
    	 
     }
     
     /**
      * 代码提交请求
      * @return
      */
     @RequestMapping("/comment/commit")
     public @ResponseBody Map commitComment(Integer parentId, Integer typeId , Integer type, String content, HttpSession session){
    	 Integer errorCode = ErrorCodes.SUCCESS;
    	 Map map = new HashMap();
    	 User user = (User) session.getAttribute("user");
		Comment c = new Comment();
		c.setParentId(parentId == null ? 0 : parentId);
		c.setContent(content);
		c.setDate(new Date());
		c.setType(type);
		c.setTypeId(typeId);
		c.setUserId(user.getId());
		System.out.println("comment : " + JSON.toJSONString(c));
		commentService.insert(c);
		map.put("comment", c);
    	 map.put("errorCode", errorCode);
 		 return map; 
    	 
     }
     
     /**
      * 跳转到问题评论页面
      * @return
      */
     @RequestMapping("/comment/question")
     public @ResponseBody Map  toQuestionPage(Integer id, HttpSession session){
    	 Integer errorCode = ErrorCodes.SUCCESS;
    	 Map map = new HashMap();
    	 List<CommentModel> l =new ArrayList();
    	 q = questionService.findRecordByProperty("id", id);
    	 
    	 if(q == null) {
    		 map.put("errorCode", errorCode);
    		 return map;
    	 }
    	 User author = userService.findRecordByProperty("id", q.getUserId());
    	 User user = (User) session.getAttribute("user");
    	 HighLadderModel hlModel = null;
    	 if(user != null) {
    		 hlModel  = highLadderService.getMaxHighLadder(user.getId());
    	 }
    	 
    	 if(q != null) {
    		 list = commentService.findRecordsByHql("from Comment where type = ? and typeId = ? order by date", Comment.TYPE_QUESTION, id);
    		 list = sortComment(list);
    		 for(Comment c : list) {
    			 User u = userService.findRecordByProperty("id", c.getUserId());
    			 HighLadderModel hl  = highLadderService.getMaxHighLadder(u.getId());
    			 
    			 String format = DateUtil.formater1.format(c.getDate());
    			 
    			 CommentModel model = new CommentModel();
    			 try {
					BeanUtils.copyProperties(model, c);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			if(user != null && user.getId().equals(c.getUserId())) {
    				model.setSelf(true);
    			}
    			model.setTitle(hl.getTitle());
    			model.setDate(format);
    			model.setAuthor(u.getUsername());
    			l.add(model);
    		 }
    	 }
    	 String inputExam = FileUtil.getStringFromFile(Constants.getInputPath(id) + "0.in");
    	 String outputExam = FileUtil.getStringFromFile(Constants.getOutputPath(id) + "0.out");
    	 map.put("inputExam", inputExam);
    	 map.put("outputExam", outputExam);
    	 map.put("question", q);
    	 map.put("author", author);
    	 map.put("errorCode", errorCode);
    	 map.put("list", l);
    	 map.put("hlModel", hlModel);
 		 return map; 
     }
     public List<Comment> sortComment(List<Comment> list){
    	 List<Comment> l =new ArrayList();
    	 HashMap map =new HashMap();
    	 for(Comment c : list) {
    		 if(c.getParentId() != 0) {
    			List<Comment> temp = (List<Comment>) map.get(c.getParentId());
    			if(temp != null) {
    				temp.add(c);
    			}else {
    				temp = new ArrayList();
    			}
    		 }else {
    			 List<Comment> temp = new ArrayList();
    			 temp.add(c);
    			 map.put(c.getId(), temp);
    		 }
    	 }
    	 for(Object index : map.keySet()) {
    		 Integer ind = (Integer)index;
    		 List<Comment> temp = (List<Comment>) map.get(ind);
    		 for(Comment c : temp) {
    			 l.add(c);
    		 }
    	 }
    	 return l;
     }
	public Question getQ() {
		return q;
	}
	public void setQ(Question q) {
		this.q = q;
	}
	public List<Comment> getList() {
		return list;
	}
	public void setList(List<Comment> list) {
		this.list = list;
	}
	public Code getCode() {
		return code;
	}
	public void setCode(Code code) {
		this.code = code;
	}
     
}
