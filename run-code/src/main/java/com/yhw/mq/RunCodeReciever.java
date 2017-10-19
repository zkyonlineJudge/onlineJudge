package com.yhw.mq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.apache.log4j.Logger;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;
import com.rabbitmq.client.ShutdownSignalException;
public class RunCodeReciever{
	public static Logger logger = Logger.getLogger(RunCodeReciever.class);

	//192.168.232.129
	private final String HOST_NAME = "192.168.232.129";
	
	public static String QUEUE_NAME = "RUN_CODE";
	
	private Channel channel = null;
	
	private Connection connection = null;
	
	private QueueingConsumer consumer;
	
	public RunCodeReciever() throws IOException, TimeoutException{
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(HOST_NAME);
		factory.setPort(5672);
		factory.setUsername("fxy");
		factory.setPassword("123456");
		this.connection = factory.newConnection();
		this.channel = connection.createChannel();
		this.channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		this.channel.basicQos(0, 1, true);
		this.consumer = new QueueingConsumer(this.channel);
		this.channel.basicConsume(QUEUE_NAME, 
				false, 	//是否自动ACK 
				this.consumer);
	}
	
	/**
	 * 指定管道名称建立队列连接
	 * @param msgQueue
	 */
	public RunCodeReciever(String msgQueue) {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(HOST_NAME);
		factory.setPort(5672);
		factory.setUsername("ozipper");
		factory.setPassword("lili8483235");
		try {
			this.connection = factory.newConnection();
			this.channel = connection.createChannel();
			this.channel.queueDeclare(msgQueue, true, false, false, null);
			this.channel.basicQos(0, 1, true);
			this.consumer = new QueueingConsumer(this.channel);
			this.channel.basicConsume(msgQueue, 
					false, 	//是否自动ACK 
					this.consumer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 返回下一个update
	 * @return
	 * @throws InterruptedException 
	 * @throws ConsumerCancelledException 
	 * @throws ShutdownSignalException 
	 * @throws IOException 
	 */
	public Delivery nextDelivery() throws ShutdownSignalException, ConsumerCancelledException, InterruptedException, IOException{
		Delivery delivery = consumer.nextDelivery(1000L);
		return delivery;
	}
	
	public void sendAck(Delivery delivery) throws IOException{
		consumer.getChannel().basicAck(delivery.getEnvelope().getDeliveryTag(), false);
	}
	
	public void sendNack(Delivery delivery) throws IOException{
		consumer.getChannel().basicNack(delivery.getEnvelope().getDeliveryTag(), false,true);
	}
	
	public void close(){
		try {
			this.channel.close();
			this.connection.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		return this.connection;
	}
}
