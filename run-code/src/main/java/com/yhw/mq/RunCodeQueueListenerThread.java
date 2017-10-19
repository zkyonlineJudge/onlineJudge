package com.yhw.mq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.QueueingConsumer.Delivery;
import com.yhw.model.QuestionDTO;

public class RunCodeQueueListenerThread implements Runnable{
	public static Integer sleepTime = null;
	private Boolean shouldStop;
	private RunCodeThreadPool runCodeThreadPool = null;
	
	private RunCodeReciever runCodeReciever;
	
	public RunCodeQueueListenerThread(Integer sleepTime) {
		this.sleepTime = sleepTime;
		shouldStop = false;
		this.runCodeThreadPool = new RunCodeThreadPool();
	}
	
	public void run() {
		if(runCodeReciever == null){
			try {
				runCodeReciever = new RunCodeReciever();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		try {
			while(!shouldStop){
				if(runCodeThreadPool.getThreadCount() >= 5){
					//newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。不让线程队列中的东西
					Thread.sleep(sleepTime);
					continue;
				}else {
					Delivery delivery;
					delivery = runCodeReciever.nextDelivery();
					if(delivery != null && delivery.getBody() != null){
						String msg = new String(delivery.getBody());
						System.out.println(msg);
						QuestionDTO questionDTO = JSON.parseObject(msg, QuestionDTO.class);
						if (questionDTO != null) {
							runCodeThreadPool.getThread(questionDTO);
						} else {
						}
						runCodeReciever.sendAck(delivery);
					}
				}
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			runCodeReciever.close();
		}
	}

}
