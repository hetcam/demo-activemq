/**
 * 
 */
package com.demo.activemq.util;

/**
 * This class is to define enums, util method or global methods
 * @author camilo
 *
 */
public class Util {
	/**
	 * This method define the operation type when queue is being processing
	 *
	 */
	public enum OperationType {
		READ(1), WRITE(2), BOTH(3);
		private int value;
 
		private OperationType(int value) {
			this.value = value;
		}
		
		public int getValue() {
	        return this.value;
	    } 
	}
	/**
	 * This method define the queue state
	 *
	 */
	public enum State {
		INIT("initial"), 
		PROCESSED("processing"),
		ENQUEUED("enqueued"),
		DELIVERED("delivered"),
		FAILED("failed");
		private String value;
 
		private State(String value) {
			this.value = value;
		}
		
		public String getValue() {
	        return this.value;
	    } 
	}
}
