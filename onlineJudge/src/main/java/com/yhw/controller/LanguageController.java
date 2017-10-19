package com.yhw.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yhw.entity.Language;
import com.yhw.serviceImpl.LanguageService;

@Controller
public class LanguageController {
     @Autowired
     LanguageService languageService;
     @RequestMapping("/language/list")
     @ResponseBody
     //获取所有语言
     public Map<String , Object> AllList(){
    	 List<Language> list = languageService.findAllRecords();
    	 Map<String,Object> map = new HashMap<String,Object>();
    	 map.put("errorCode", 0);
 		 map.put("list", list);
 		 map.put("msg", "成功");
 		 return map; 
    	 
     }
}
