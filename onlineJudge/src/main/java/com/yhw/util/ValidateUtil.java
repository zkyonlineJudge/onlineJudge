package com.yhw.util;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 验证码生成器
 * @author fxy
 *
 */
public class ValidateUtil {
	private static Object[] arr;
	
	public static int VALIDATENUM = 6;
	
	public static void init() {
		List list =new LinkedList();
		for(int i = 0 ;i < 10; i++) {
			list.add(i);
		}
		for(int i = 0 ;i < 26 ; i++) {
			char a = 'a';
			list.add((char)(a + i));
		}
		for(int i = 0 ;i < 26 ; i++) {
			char a = 'A';
			list.add((char)(a + i));
		}
		arr = list.toArray();
	}
	/**
	 * 生成随机码调用这个接口
	 * @return
	 */
	public static String getCode() {
		StringBuffer sb = new  StringBuffer();
		init();
		for(int i = 0 ;i < ValidateUtil.VALIDATENUM; i++) {
			int j = (int)(Math.random() * arr.length);
			sb.append(arr[j]);
		}
		return sb.toString();
	}
	public static void main(String[] args) {
		StringBuffer sb = new  StringBuffer();
		init();
		for(int i = 0 ;i < ValidateUtil.VALIDATENUM; i++) {
			int j = (int)(Math.random() * arr.length);
			sb.append(arr[j]);
		}
	}
}
