/**
 * 
 */
package com.demo.activemq.app;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Service;

import com.demo.activemq.domain.Queue;

/**
 * @author camilo
 *
 */
@Service
public class JmsMessageSender {
	@Autowired
	private JmsTemplate jmsTemplate;

	/**
	 * send text to default destination
	 * 
	 * @param text
	 */
	public void send(final String text) {
		this.jmsTemplate.send(new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				Message message = session.createTextMessage(text);
				// set ReplyTo header of Message, pretty much like the concept
				// of email.
				message.setJMSReplyTo(new ActiveMQQueue("QueueDefault"));
				return message;
			}
		});
	}

	/**
	 * Simplify the send by using convertAndSend
	 * 
	 * @param text
	 */
	public void sendText(final String text) {
		this.jmsTemplate.convertAndSend(text);
	}

	/**
	 * Send text message to a specified destination or queue
	 * 
	 * @param text
	 */
	public void send(final Destination dest, final String text) {

		this.jmsTemplate.send(dest, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				Message message = session.createTextMessage(text);
				System.out.println("JMSMessageID is : "+message.getJMSMessageID());
				return message;
			}
		});
	}
	/**
	 * Set message with conversion
	 * @param dest
	 * @param text
	 */
	public void sendWithConversion(final Destination dest, final String text) {
	    Map<String,Object> map = new HashMap<String,Object>();
	    map.put("ClientID", 2346);
	    map.put("OperationType", "Read"); // Read/Write/Both
	    this.jmsTemplate.convertAndSend(dest, map, new MessagePostProcessor() {
	        public Message postProcessMessage(Message message) throws JMSException {
	            message.setJMSMessageID("ID:camilo-PC-55075-1453371865916-1:1:4:1:2-custom");
				message.setJMSTimestamp(new Timestamp(new Date().getTime()).getTime());
	            message.setJMSCorrelationID( (String.valueOf(new Random().nextInt(999999)+1000)) + "-002");
	            return message;
	        }
	    });
	}
	/**
	 * Set message with conversion by Queue
	 * @param queue
	 */
	public void sendWithConversion(Queue queue) {
	    Map<String,Object> map = new HashMap<String,Object>();
	    map.put("ClientID", queue.getClientID());
	    map.put("OperationType", queue.getOperationType()); // Read/Write/Both
	    this.jmsTemplate.convertAndSend(queue.getName(), map, new MessagePostProcessor() {
	        public Message postProcessMessage(Message message) throws JMSException {
	            message.setJMSMessageID(queue.getjMSMessageID());
				message.setJMSTimestamp(queue.getjMSTimestamp());
	            message.setJMSCorrelationID(queue.getjMSCorrelationID());
	            return message;
	        }
	    });
	}
}
