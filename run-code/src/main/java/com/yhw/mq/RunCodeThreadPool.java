package com.yhw.mq;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.yhw.model.QuestionDTO;

public class RunCodeThreadPool {
	private static ExecutorService pools = Executors.newFixedThreadPool(4);
	
	//多个对象共享这个static变量
	private static Integer threadCount = 0;
	
	public void getThread(QuestionDTO questionDTO){
		pools.execute(new Thread(new RunCodeThread(questionDTO)));
		threadCount++;
	}
	
	public static Integer getThreadCount(){
		return threadCount;
	}
	
	public static void deleteThreadCount(){
		threadCount--;
	}
	public static void stopService(){
		pools.shutdown();
	}
}
