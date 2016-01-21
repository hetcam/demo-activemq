/**
 * 
 */
package com.demo.activemq.app;

import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.activemq.command.ActiveMQMapMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

/**
 * This class is a listener for queues
 * @author camilo
 *
 */
@Service
public class JmsMessageListener implements MessageListener {
	/**
	 * This methos receive the message
	 * @param message
	 * @throws JMSException
	 */
	@JmsListener(destination = "queue.foo")
	public void processMessage(Message message) throws JMSException {
		ActiveMQMapMessage amq = (ActiveMQMapMessage)message;
		Map<String,Object> map = amq.getContentMap();
		System.out.println("Received JMSMessageID: " + message.getJMSMessageID());
		System.out.println("Received JMSTimestamp: " + message.getJMSTimestamp());
		System.out.println("Received JMSCorrelationID: " + message.getJMSCorrelationID());
		System.out.println("Received ClientID: " + map.get("ClientID"));
		System.out.println("Received OperationType: " + map.get("OperationType"));
	}
	/**
	 * This method is when destination is by default
	 */
	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			try {
				System.out.println("Mssg ID: "+message.getJMSMessageID());
				String msg = ((TextMessage) message).getText();
				System.out.println("Message has been consumed : " + msg);
			} catch (JMSException ex) {
				throw new RuntimeException(ex);
			}
		} else {
			throw new IllegalArgumentException("Message must be of type TextMessage");
		}
	}

}
