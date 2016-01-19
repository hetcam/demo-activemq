/**
 * 
 */
package com.demo.activemq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

/**
 * @author camilo
 *
 */
@Service
public class JmsMessageListener implements MessageListener {
	//@JmsListener(destination = "SendToRecv")
	@JmsListener(destination = "AnotherDest")
	@SendTo("AnotherDest")
	public String processMessage(String text) {
		System.out.println("Received: " + text);
		return "ACK from handleMessage";
	}
	
	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			try {
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
