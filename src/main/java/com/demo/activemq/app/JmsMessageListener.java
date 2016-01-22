/**
 * 
 */
package com.demo.activemq.app;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.activemq.command.ActiveMQObjectMessage;
import org.apache.log4j.Logger;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import com.demo.activemq.dao.AccessDAO;
import com.demo.activemq.domain.Queue;
import com.demo.activemq.util.Util.State;

/**
 * This class is a listener for queues
 * @author camilo
 *
 */
@Service
public class JmsMessageListener implements MessageListener {
	
	final static Logger log = Logger.getLogger(JmsMessageListener.class);
	/**
	 * This method receive the message when is custom destination
	 * @param message
	 * @throws JMSException
	 */
	@JmsListener(destination = "one-queue.foo")
	public void processMessage(Message message) throws JMSException {
		log.debug("Receiving the message ... ");
		Queue queue = null;
		try {
			ActiveMQObjectMessage amq = (ActiveMQObjectMessage)message;
			queue = (Queue) amq.getObject();
			queue.setState(State.DELIVERED);
			log.info("Received JMSMessageID: " + message.getJMSMessageID());
			log.info("Received JMSTimestamp: " + message.getJMSTimestamp());
			log.info("Received JMSCorrelationID: " + message.getJMSCorrelationID());
			log.info("Received ClientID: " + queue.getClientID());
			log.info("Received OperationType: " + queue.getOperationType());
			
			AccessDAO.persistQueue(queue);
			
		} catch (Exception e) {
			log.error("An error occurred when the message is received",e);
		}
	}
	/**
	 * This method is used when is default destination
	 */
	@Override
	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			try {
				log.info("Mssg ID: "+message.getJMSMessageID());
				String msg = ((TextMessage) message).getText();
				log.info("Message has been consumed : " + msg);
				
			} catch (JMSException ex) {
				throw new RuntimeException(ex);
			}
		} else {
			throw new IllegalArgumentException("Message must be of type TextMessage");
		}
	}
	
	

}
