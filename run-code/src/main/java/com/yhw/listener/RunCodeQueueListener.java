package com.yhw.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServlet;

import com.yhw.mq.RunCodeQueueListenerThread;

//import com.proshot.service.mq.RetouchConfirmListenerThread;

public class RunCodeQueueListener extends HttpServlet implements ServletContextListener{

		private static final long serialVersionUID = 1L;

		private static Integer sleepTime = 3000;
		private Thread runCodeQueueListenerThread = null;
		//private Thread retouchConfirmListenerThread = null;

		public void contextDestroyed(ServletContextEvent arg0) {
			runCodeQueueListenerThread.interrupt();
			System.out.println("销毁了");
		}

		public void contextInitialized(ServletContextEvent arg0) {
			System.out.println("初始化");
			runCodeQueueListenerThread = new Thread(new RunCodeQueueListenerThread(sleepTime));
			runCodeQueueListenerThread.start();
//			resizeUploadListenerThread = new Thread(new ResizeUploadListenerThread(sleepTime));
//			resizeUploadListenerWorkThread.start();
		}
}
