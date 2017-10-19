package com.yhw.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 常量定义类
 *
 * @author
 * @since 2.0
 */
public class Constants {

    //静态资源oss前缀、部署时间
    public static final String DP = "/"; 
    
    public static final String DT = "";
    
    public static String workSpace = "E:" + File.separator + "test" + File.separator;
    
	public static String getInputPath(Integer id) {
		// TODO Auto-generated method stub
		return workSpace + "in" + File.separator + id + File.separator;
		
	}
	public static String getOutputPath(Integer id) {
		// TODO Auto-generated method stub
		return workSpace + "out" + File.separator + id + File.separator;
	}
	public static String getResultOutputPath(Integer id) {
		// TODO Auto-generated method stub
		return workSpace + "result" + File.separator + id + File.separator;
	}
	
	public static Map<String, String> langMap;
	
	
	/**
	 * 1
	 */
	public static final Integer YES = 1;
	/**
	 * 0
	 */
	public static final Integer NO = 0;


	/**
	 * 已删除
	 */
	public static final int DELETED_YES = 1;

	/**
	 * 未删除
	 */
	public static final int DELETED_NO = 0;
	
	static {
		langMap = new HashMap();
		langMap.put("c", "gcc");
		langMap.put("java", "java");
		langMap.put("c++", "g++");
		langMap.put("python", "python");
	}
}
