/**
 * 
 */
package com.demo.activemq.app;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.demo.activemq.dao.AccessDAO;
import com.demo.activemq.domain.Queue;
//import com.demo.activemq.domain.Queue;
import com.demo.activemq.util.Constant;
import com.demo.activemq.util.Util.OperationType;
import com.demo.activemq.util.Util.State;;



/**
 * This class is the principal for application and permit his execution. 
 * 
 * @author camilo
 *
 */
public class App {
	
	final static Logger log = Logger.getLogger(App.class);
	
	public static void main(String[] args) {
		
		log.info("Init the process");
		
		// init spring context
		ApplicationContext ctx = new ClassPathXmlApplicationContext("app-context.xml");
		
		// get bean from context
		JmsMessageSender jmsMessageSender = (JmsMessageSender) ctx.getBean("jmsMessageSender");

		// send to default destination
		//jmsMessageSender.send("hello JMS");
		
		// send text to default destination
		//jmsMessageSender.sendText("Another text");

		// send to a code specified destination
		//javax.jms.Queue queue = new ActiveMQQueue("queue.foo");
		//jmsMessageSender.send(queue, "hello");
		
		log.debug("Generating messages for : "+Constant.QUEUE_ONE);
		for(int i = 0; i < 20; i++) {
			Queue queue = new Queue();
			queue.setName(Constant.QUEUE_ONE);
			queue.setState(State.INIT);
			int clientID = new Random().nextInt(1000) + 1000;
			queue.setClientID("ID:"+String.valueOf(clientID));
			String correlationID = (String.valueOf(new Random().nextInt(999999)+100000)) + "-002";
			queue.setjMSCorrelationID(correlationID);
			queue.setjMSTimestamp(new Timestamp(new Date().getTime()).getTime());
			queue.setjMSMessageID("ID:message-PC-"+clientID+"-1453371865916-1:1:4:1:2");
			OperationType[] ope = OperationType.values();
			queue.setOperationType(ope[new Random().nextInt(ope.length)]);
			
			AccessDAO.persistQueue(queue);
			
			// send message
			jmsMessageSender.sendWithConversion(queue);
		}
		log.debug("Generating messages for : "+Constant.QUEUE_TWO);
		for(int i = 0; i < 20; i++) {
			Queue queue = new Queue();
			queue.setName(Constant.QUEUE_TWO);
			queue.setState(State.INIT);
			int clientID = new Random().nextInt(1000) + 1000;
			queue.setClientID("ID:"+String.valueOf(clientID));
			String correlationID = (String.valueOf(new Random().nextInt(999999)+100000)) + "-002";
			queue.setjMSCorrelationID(correlationID);
			queue.setjMSTimestamp(new Timestamp(new Date().getTime()).getTime());
			queue.setjMSMessageID("ID:message-PC-"+clientID+"-1453371865916-1:1:4:1:2");
			OperationType[] ope = OperationType.values();
			queue.setOperationType(ope[new Random().nextInt(ope.length)]);
			
			AccessDAO.persistQueue(queue);
			
			// send message
			jmsMessageSender.sendWithConversion(queue);
		}

		// close spring application context
		((ClassPathXmlApplicationContext) ctx).close();
	}
}
