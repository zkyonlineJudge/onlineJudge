package com.yhw.util;

import java.io.IOException;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.yhw.model.QuestionDTO;

public class SendMessageUtil {
	public static String QUEUE_NAME = "RUN_CODE";
	public static String HOST = "192.168.232.129";
	public static void main(String[] args) throws IOException {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();

        //设置RabbitMQ相关信息
        factory.setHost(HOST);
        factory.setUsername("fxy");
        factory.setPassword("123456");
        factory.setPort(5672);

        //创建一个新的连接
        Connection connection = factory.newConnection();

        //创建一个通道
        Channel channel = connection.createChannel();

        // 声明一个队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //发送消息到队列中
        String message = "Hello RabbitMQ";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
        System.out.println("Producer Send +'" + message + "'");

        //关闭通道和连接
        channel.close();
        connection.close();
	}
	public static void send(QuestionDTO dto) {
		//创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置RabbitMQ相关信息
        factory.setHost(HOST);
        factory.setUsername("fxy");
        factory.setPassword("123456");
        factory.setPort(5672);

        //创建一个新的连接
        Connection connection;
		try {
			connection = factory.newConnection();
			//创建一个通道
	        Channel channel = connection.createChannel();

	        // 声明一个队列
	        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

	        //发送消息到队列中
	        String message = JSON.toJSONString(dto);
	        channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
	        System.out.println("Producer Send +'" + message + "'");
	        //关闭通道和连接
	        channel.close();
	        connection.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
