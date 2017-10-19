package com.yhw.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yhw.entity.Language;
import com.yhw.entity.Post;
import com.yhw.entity.User;
import com.yhw.model.PostModel;
import com.yhw.serviceImpl.LanguageService;
import com.yhw.serviceImpl.PostService;

@Controller
public class PostController {
	@Autowired
    PostService postService;
	@Autowired
	LanguageService languageService;
	@RequestMapping(value = "/post/list")
	@ResponseBody
	//根据语言获取前4个公告 按时间排序
	public Map<String,Object> listLimit(@RequestParam Integer lid){
		List<PostModel> list =  (List<PostModel>) postService.listLimit(lid);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("errorCode", 0);
		map.put("list", list);
		map.put("msg", "成功");
		return map; 	
	}
	@RequestMapping(value = "/post/managelist")
	@ResponseBody
	//后台公告列表
	public Map<String,Object> managelist(String title,String languge,Integer countSize,Integer countPage){
		List<PostModel> list =postService.postList(title, languge, countSize, countPage);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("errorCode", 0);
		map.put("list", list);
		map.put("msg", "成功");
		return map; 	
	}
	@RequestMapping(value = "/post/all/managelist")
	@ResponseBody
	//后台公告列表
	public Map<String,Object> manageAllList(){
		List<PostModel> list =postService.postList();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("errorCode", 0);
		map.put("list", list);
		map.put("msg", "成功");
		return map; 	
	}
	
	//根据公告id查公告信息
	@RequestMapping(value = "/post/bypidlist")
	@ResponseBody
	public Map<String,Object> listBypid(Integer pid){
		PostModel list = postService.listBypid(pid);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("errorCode", 0);
		map.put("list", list);
		map.put("msg", "成功");
		return map; 
	}
	//公告新增
	@RequestMapping(value="/post/save")
	@ResponseBody
	public Map<String,Object> postSave(Post post,String language){
		Language lg = languageService.findRecordByProperty("language", language);
		post.setLanguageId(lg.getId());
		post.setAuthorId(1);//从session中拿Authid
		post.setDate(new Date());
		Integer integer =postService.save(post);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", integer);
		map.put("errorCode", 0);
		map.put("msg", "成功");
		return map; 	
	}
	//公告更新
	@RequestMapping(value="/post/update")
	@ResponseBody
	public Map<String,Object> postUpdate(HttpSession session, Post post,String language){
		User user = (User) session.getAttribute("user");
		Language lg = languageService.findRecordByProperty("language", language);
		post.setLanguageId(lg.getId());
		post.setAuthorId(user.getId());//从session中获取Authid
		post.setDate(new Date());
		Post pt=postService.update(post);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", post);
		map.put("errorCode", 0);
		map.put("msg", "成功");
		return map; 	
	}
	//批量删除
	@RequestMapping(value="/post/batchdelete")
	@ResponseBody
	public Map<String,Object> batchDelete(String ids){
		String[] id = ids.split(",");
		List<Integer> idList =new ArrayList<Integer>();
		for (int i=0;i<id.length;i++){
			int a = new Integer(id[i]);
			idList.add(a);
			}
		Integer integer=postService.batchDelete(idList);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("errorCode", 0);
		map.put("msg", "成功");
		return map; 
	}
	//单一删除
	@RequestMapping(value="/post/deletebyid")
	@ResponseBody
	public Map<String,Object> deleteById(Post post){
		Post p=postService.delete(post);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", p);
		map.put("errorCode", 0);
		map.put("msg", "成功");
		return map; 
	}
	
	
}
