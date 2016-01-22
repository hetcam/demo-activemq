/**
 * 
 */
package com.demo.activemq.app;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Service;

import com.demo.activemq.dao.AccessDAO;
import com.demo.activemq.domain.Queue;
import com.demo.activemq.util.Util.State;

/**
 * This class is to send all messages 
 * @author camilo
 *
 */
@Service
public class JmsMessageSender {
	
	final static Logger log = Logger.getLogger(JmsMessageListener.class);
	
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
				log.info("JMSMessageID is : "+message.getJMSMessageID());
				message.setJMSMessageID("ID:message-2253");
				return message;
			}
		});
	}
	/**
	 * Set message and transform headers and properties
	 * @param queue
	 */
	public void sendWithConversion(Queue queue) {
		log.debug("Sending message ... ");
	    queue.setState(State.PROCESSED);
	    this.jmsTemplate.convertAndSend(queue.getName(), queue, new MessagePostProcessor() {
	        public Message postProcessMessage(Message message) throws JMSException {
	            message.setJMSMessageID(queue.getjMSMessageID());
				message.setJMSTimestamp(queue.getjMSTimestamp());
	            message.setJMSCorrelationID(queue.getjMSCorrelationID());
	            AccessDAO.persistQueue(queue);
	            return message;
	        }
	    });
	}
}
