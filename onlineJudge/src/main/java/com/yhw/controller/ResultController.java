package com.yhw.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yhw.model.ResultModel;
import com.yhw.serviceImpl.ResultService;

@Controller
public class ResultController {
	@Autowired
	ResultService resultService;
	@RequestMapping("/result/list")
	@ResponseBody
	public Map<String,Object> list(Integer countSize,Integer countPage){
		 List<ResultModel> list = resultService.resultList();
		 Map<String,Object> map = new HashMap<String,Object>();
    	 map.put("errorCode", 0);
 		 map.put("list", list);
 		 map.put("msg", "成功");
 		 return map; 
		
	}

}
