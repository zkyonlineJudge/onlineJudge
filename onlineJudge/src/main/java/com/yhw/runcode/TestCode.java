package com.yhw.runcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class TestCode {
	private Map<String , String> compileMap = new HashMap();
	private Map<String , String> runMap = new HashMap();
	public void init() {
		compileMap.put("gcc", "gcc main.c -o main -Wall -lm -O2 -std=c99 --static -DONLINE_JUDGE");
		compileMap.put("g++", "g++ main.cpp -O2 -Wall -lm --static -DONLINE_JUDGE -o main");
		compileMap.put("java", "javac E:\\学习\\python\\Lo-runner-master\\Test.java");
		compileMap.put("ruby", "ruby -c main.rb");
		compileMap.put("perl", "perl -c main.pl");
		compileMap.put("pascal", "fpc main.pas -O2 -Co -Ct -Ci");
		compileMap.put("go", "/opt/golang/bin/go build -ldflags \"-s -w\"  main.go");
		compileMap.put("lua", "luac -o main main.lua");
		compileMap.put("python2", "python2 -m py_compile main.py");
		compileMap.put("python3", "python3 -m py_compile main.py");
		compileMap.put("haskell", "ghc -o main main.hs");
		
		runMap.put("java", "java E:\\学习\\python\\Lo-runner-master\\Test");
	}
	public static void main(String[] args) {
		TestCode test =new TestCode();
		test.init();
		Map<String, String> compileMap = test.getCompileMap();
		Map<String, String> runMap = test.getRunMap();
		String language = "java";
		Runtime runtime =Runtime.getRuntime();
		try {
//			Process exec = runtime.exec(compileMap.get(language));
			Process exec = runtime.exec("ls");
			InputStream is = exec.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String str=null;
			System.out.println(compileMap.get(language));
			while((str = br.readLine()) != null) {
				System.out.println(str);
				
			}
			exec.destroy();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Map<String, String> getCompileMap() {
		return compileMap;
	}
	public void setCompileMap(Map<String, String> compileMap) {
		this.compileMap = compileMap;
	}
	public Map<String, String> getRunMap() {
		return runMap;
	}
	public void setRunMap(Map<String, String> runMap) {
		this.runMap = runMap;
	}
	
}
