/**
 * 
 */
package com.demo.activemq.domain;

/**
 * This class is a entity for mapping table queue_messages
 * 
 * @author camilo
 *
 */
public class Queue {

	String jMSMessageID; // : ID:camilo-PC-55079-1453371901992-1:1:4:1:1
	long jMSTimestamp; // 1453371902148
	String jMSCorrelationID; // 73670-002
	String clientID; // 2346
	String operationType; // Read

	String name;
	String state;

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

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
