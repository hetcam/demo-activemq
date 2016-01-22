/**
 * 
 */
package com.demo.activemq.domain;

import java.io.Serializable;

import com.demo.activemq.util.Util.OperationType;
import com.demo.activemq.util.Util.State;

/**
 * This class is a entity for mapping table queue_messages
 * 
 * @author camilo
 *
 */
public class Queue implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8835844991858966662L;
	String jMSMessageID; // : ID:camilo-PC-55079-1453371901992-1:1:4:1:1
	long jMSTimestamp; // 1453371902148
	String jMSCorrelationID; // 73670-002
	String clientID; // 2346
	OperationType operationType; // Read
	String name;
	State state;

	public String getjMSMessageID() {
		return jMSMessageID;
	}

	public void setjMSMessageID(String jMSMessageID) {
		this.jMSMessageID = jMSMessageID;
	}

	public long getjMSTimestamp() {
		return jMSTimestamp;
	}

	public void setjMSTimestamp(long jMSTimestamp) {
		this.jMSTimestamp = jMSTimestamp;
	}

	public String getjMSCorrelationID() {
		return jMSCorrelationID;
	}

	public void setjMSCorrelationID(String jMSCorrelationID) {
		this.jMSCorrelationID = jMSCorrelationID;
	}

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public OperationType getOperationType() {
		return operationType;
	}

	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

}
