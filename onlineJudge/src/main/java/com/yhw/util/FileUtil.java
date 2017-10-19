package com.yhw.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Date;

public class FileUtil {
	public static boolean exitFile(String path) {
		File file = new File(path);
		if(file.exists()) {
			return true;
		}
		return false;
	}
	/**
	 * 获取文件中的内容
	 * @param path
	 * @return
	 */
	public static String getStringFromFile(String path) {
		StringBuffer sb = new StringBuffer();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
			String str = null;
			while((str = br.readLine()) != null) {
				sb.append(str);
			}
			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sb.toString();
	}
	/**
	 * 获取到文件中的换行符
	 * @param path
	 * @return
	 */
	public static String getStringFromFileN(String path) {
		StringBuffer sb = new StringBuffer();
		try {
			FileInputStream br = new FileInputStream(path);
			byte[] arr = new byte[1024];
			int len = 0;
			while((len = br.read(arr)) > 0) {
				sb.append(new String(arr, 0 , len, "utf-8"));
			}
			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sb.toString();
	}
	/**
	 * 把目标文件的所有父文件建立好，然后往目标文件写入数据
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException
	 */
	public static boolean writeToFile(String path, String content) throws IOException {
		File file = new File(path);
		File pf = file.getParentFile();
		if(!pf.exists()) {
			pf.mkdirs();
		}
		if(!file.exists()) {
			file.createNewFile();
		}
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file))); 
		bw.write(content);
		bw.flush();
		bw.close();
		return true;
	}
	public static void main(String[] args) throws InterruptedException {
//		String stringFromFile = FileUtil.getStringFromFile("E:\\2\\Main.java");
//		System.out.println(stringFromFile);
//		int inputCount = FileUtil.getInputCount("E:\\3\\");
//		System.out.println(inputCount);
		Date date = new Date();
		Thread.sleep(1000);
		Date d =new Date();
		System.out.println(date.getTime() - d.getTime());
		
	}
	public static int getInputCount(String path) {
		// TODO Auto-generated method stub
		File file = new File(path);
		if(file.isDirectory()) {
			String[] list = file.list();
			return list.length;
		}
		return 0;
	}
}
