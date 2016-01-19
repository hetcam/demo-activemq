/**
 * 
 */
package com.demo.activemq;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



/**
 * Class for testing
 * 
 * @author camilo
 *
 */
public class DemoMain {
	public static void main(String[] args) {
		// init spring context
		ApplicationContext ctx = new ClassPathXmlApplicationContext("app-context.xml");

		// get bean from context
		JmsMessageSender jmsMessageSender = (JmsMessageSender) ctx.getBean("jmsMessageSender");
		
		// test for save messages in database
		try {
			BasicDataSource ds = (BasicDataSource)ctx.getBean("mysql-ds");
			Connection conn = (Connection) ds.getConnection();
			String sql = "INSERT INTO queue_messages(queue_name,message,state) VALUES (?,?,?);";
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, "queueone");
			stm.setString(2, "test");
			stm.setString(3, "test");
			int result = stm.executeUpdate();
			System.out.println(result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		// send to default destination
		jmsMessageSender.send("hello JMS");

		// send to a code specified destination
		Queue queue = new ActiveMQQueue("QueueOne");
		
		jmsMessageSender.send(queue, "hello this Message is for QueueOne,MQ1,125369,65323");
		
		
		// send text to default destination
		jmsMessageSender.sendText("Another text");
		
		// close spring application context
		((ClassPathXmlApplicationContext) ctx).close();
	}
}
