package com.yhw.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yhw.entity.Auth;
import com.yhw.entity.Code;
import com.yhw.entity.User;
import com.yhw.model.HighLadderModel;
import com.yhw.serviceImpl.AuthService;
import com.yhw.serviceImpl.CodeService;
import com.yhw.serviceImpl.HighLadderService;
import com.yhw.serviceImpl.QuestionService;
import com.yhw.serviceImpl.UserService;
import com.yhw.util.ErrorCodes;
import com.yhw.util.MD5;
import com.yhw.util.MailUtil;
import com.yhw.util.ValidateUtil;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private CodeService codeService;
	@Autowired
	private HighLadderService highLadderService;
	@Autowired
	private AuthService authService;
	@RequestMapping("/login")
	public @ResponseBody Map login(String username, String password, HttpSession session) {
		Integer errorCode = ErrorCodes.SUCCESS;
		Map retMap = new HashMap();
		if(username != null && password != null) {
			username = username.trim();
//			System.out.println(password);
			password = MD5.getMd5(password.trim());
			User user = null;
			String re = "[0-9]{11}";
			if(Pattern.matches(re, username)) {
				 user = userService.findRecordByProperty("phone", username);
			}else {
				user = userService.findRecordByProperty("email", username);
			}
			System.out.println("table" + user.getPassword());
			System.out.println("submit" + password);
			 if(user != null && user.getPassword().trim().equals(password)) {
				 user.setLastLoginTime(new Date());
				 User newUser = userService.update(user);
				 newUser.setPassword("");
				 session.setAttribute("user", user);
				 retMap.put("msg", "登录成功！");
				 retMap.put("user", newUser);
			 }else {
				errorCode = ErrorCodes.LOGIN_FAIL;
				retMap.put("msg", "登录失败！");
			}
		}else {
			errorCode = ErrorCodes.LOGIN_FAIL;
			retMap.put("msg", "登录失败！");
		}
		
		
		retMap.put("errorCode", errorCode);
		return retMap;
	}
	public static void main(String[] args) {
		String re = "[\\s\\S]+@([\\s\\S]+.[\\s\\S]+)+";
		if(Pattern.matches(re, "498464483@qq.com")) {
			System.out.println("success");
		}else {
			System.out.println("fail");
		}
	}
	@RequestMapping("/registerv1")
	public @ResponseBody Map registerV1(String email, HttpSession session) {
		Integer errorCode = ErrorCodes.SUCCESS;
		Map retMap = new HashMap();
		if(null != email) {
			String code = ValidateUtil.getCode();
			session.setAttribute("code", code);
			session.setAttribute("email", email);
			String re =  "[\\s\\S]+@([\\s\\S]+.[\\s\\S]+)+";
			if(Pattern.matches(re, email)) {
				//邮箱有效
				try {
					MailUtil.postMail("尊敬的用户", email, code);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}else {
				errorCode = ErrorCodes.EMAIL_INVALID;
			}
		}else {
			errorCode = ErrorCodes.EMAIL_INVALID;
		}
		
		retMap.put("errorCode", errorCode);
		return retMap;
	}
	@RequestMapping("/registerv2")
	public @ResponseBody Map registerV2(String code, HttpSession session) {
		Integer errorCode = ErrorCodes.SUCCESS;
		Map retMap = new HashMap();
		Object originCode = session.getAttribute("code");
		if(code.trim().equals(originCode)) {
			session.setAttribute("regSuccuess", true);
		}else {
			errorCode = ErrorCodes.CODE_INVALID;
		}
		retMap.put("errorCode", errorCode);
		return retMap;
	}
	@RequestMapping("/registerv3")
	public @ResponseBody Map registerV3(String pwd, HttpSession session) {
		Integer errorCode = ErrorCodes.SUCCESS;
		Map retMap = new HashMap();
		Object flag = (Object) session.getAttribute("regSuccuess");
		if(flag != null && flag.equals(true)) {
			String email = (String) session.getAttribute("email");
			User user = new User();
			user.setEmail(email);
			user.setPassword(MD5.getMd5(pwd));
			user.setRegisterTime(new Date());
			user.setLastLoginTime(new Date());
//			user.setRankId(rankId);
//			user.setHighLadderId(highLadderId);
			User temp = userService.update(user);
			if(temp == null) {
				errorCode = ErrorCodes.DB_ERROR;
			}else {
				retMap.put("email", email);
			}
		}else {
			errorCode = ErrorCodes.CODE_INVALID;
		}
		retMap.put("errorCode", errorCode);
		return retMap;
	}
	@RequestMapping("/self")
	public @ResponseBody Map getSelf(HttpSession session) {
		Integer errorCode = ErrorCodes.SUCCESS;
		Map retMap = new HashMap();
		User user = (User) session.getAttribute("user");
		if(user != null) {
			User originUser = userService.findRecordByProperty("id", user.getId());
			Auth auth = authService.findRecordByProperty("id", originUser.getAuthId() );  
			originUser.setPassword("");
			
			retMap.put("user", originUser);
			retMap.put("auth", auth);
			
		}else {
			errorCode = ErrorCodes.LOGIN_FAIL;
		}
		retMap.put("errorCode", errorCode);
		return retMap;
	}
	@RequestMapping("/ladder/self")
	public @ResponseBody Map getLadderSelf(HttpSession session) {
		Integer errorCode = ErrorCodes.SUCCESS;
		Map retMap = new HashMap();
		User user = (User) session.getAttribute("user");
		if(user != null) {
			User originUser = userService.findRecordByProperty("id", user.getId());
			Integer[] arr = codeService.getCodeNum(user.getId(), Code.TYPE_LADDER);
			HighLadderModel model = highLadderService.getMaxHighLadder(user.getId());
//			originUser.setPassword("");
			retMap.put("qnum", arr[1]);
			retMap.put("language", model.getLanguage() == null ? "-1" : model.getLanguage());
			retMap.put("score", model.getScore() == null ? 0 :  model.getScore());
			retMap.put("title", model.getTitle());
			retMap.put("position", model.getPosition() == null ? -1 : model.getPosition());
			if(arr[1] != 0) {
				retMap.put("accuracy", 1.0 * arr[0] / arr[1]);
			}else {
				retMap.put("accuracy", 0);
			}
		}else {
			errorCode = ErrorCodes.LOGIN_FAIL;
		}
		retMap.put("errorCode", errorCode);
		return retMap;
	}
	@RequestMapping("/dynamic/self")
	public @ResponseBody Map getNynamicSelf(HttpSession session, Integer type) {
		Integer errorCode = ErrorCodes.SUCCESS;
		Map<String, Comparable> retMap = new HashMap();
		User user = (User) session.getAttribute("user");
		if(user != null) {
			User originUser = userService.findRecordByProperty("id", user.getId());
			errorCode = userService.getRecentInf(retMap, originUser.getId(), type);
		}else {
			errorCode = ErrorCodes.LOGIN_FAIL;
		}
		retMap.put("errorCode", errorCode);
		return retMap;
	}
	@RequestMapping("/update")
	@ResponseBody
	public  Map<String,Object> userSave(User user){
		User u = userService.findRecordByProperty("email",user.getEmail().trim());
		u.setPhone(user.getPhone());
		u.setUsername(user.getUsername());
		u.setDescription(user.getDescription());
		User userChanged = userService.update(u);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("errorCode", 0);
		map.put("list", userChanged);
		map.put("msg", "成功");
		return map; 	
		
	}
	@RequestMapping("/delete")
	@ResponseBody
	public  Map<String,Object> delete(User user){
		Map<String,Object> map = new HashMap<String,Object>();
		User u = userService.findRecordByProperty("id",user.getId());
		if(u != null) {
			User de = userService.delete(u);
		}
		map.put("errorCode", 0);
		map.put("msg", "成功");
		return map; 	
	}
	@RequestMapping("/list")
	@ResponseBody
	public  Map<String,Object> listUser(){
		Map<String,Object> map = new HashMap<String,Object>();
		 List<User> findAllRecords = userService.findAllRecords();
		map.put("errorCode", 0);
		map.put("list",findAllRecords);
		map.put("msg", "成功");
		return map; 	
	}
	@RequestMapping("/pwd/modify")
	@ResponseBody
	public  Map<String,Object> passwordModify(String pwd, String npwd,HttpSession session){
		User user = (User) session.getAttribute("user");
		User u = userService.findRecordByProperty("id", user.getId());
		Integer errorCode = 0;
		boolean flag = MD5.getMd5(pwd).equals(u.getPassword());
		if(flag){
			u.setPassword(MD5.getMd5(npwd));
			User userChanged = userService.update(u);
		}else{
			errorCode = 1;
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("errorCode", errorCode);
		return map; 	
		
	}
}
