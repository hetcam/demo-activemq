/**
 * 
 */
package com.demo.activemq.app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Random;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.demo.activemq.dao.AccessDAO;
import com.demo.activemq.domain.Queue;



/**
 * Class for testing
 * 
 * @author camilo
 *
 */
public class App {
	
	
	public static void main(String[] args) {
		
		// init spring context
		ApplicationContext ctx = new ClassPathXmlApplicationContext("app-context.xml");
		
		// get bean from context
		JmsMessageSender jmsMessageSender = (JmsMessageSender) ctx.getBean("jmsMessageSender");
		
		//AccessDAO.persistQueue(queue);
		
		// test for save messages in database
		/*try {
			BasicDataSource ds = (BasicDataSource)ctx.getBean("mysql-ds");
			Connection conn = (Connection) ds.getConnection();
			String sql = "INSERT INTO queue_messages(queue,message,state) VALUES (?,?,?);";
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, "queueone");
			stm.setString(2, "test");
			stm.setString(3, "test");
			int result = stm.executeUpdate();
			System.out.println("Insert in database was: "+(result>0?"success":"failed"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		

		// send to default destination
		//jmsMessageSender.send("hello JMS");

		// send to a code specified destination
		//Queue queue = new ActiveMQQueue("Queue.one");
		//jmsMessageSender.sendWithConversion(queue, "hello");
		
		
		Queue queue = new Queue();
		queue.setName("queue.foo");
		int clientID = new Random().nextInt(1000) + 1000;
		queue.setClientID("ID:"+String.valueOf(clientID));
		jmsMessageSender.sendWithConversion(queue);
		
		// send text to default destination
		//jmsMessageSender.sendText("Another text");
		
		// close spring application context
		((ClassPathXmlApplicationContext) ctx).close();
	}
}
