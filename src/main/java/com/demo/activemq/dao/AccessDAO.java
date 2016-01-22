/**
 * 
 */
package com.demo.activemq.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.demo.activemq.domain.Queue;

/**
 * This class is to access to database: insert, update, delete the data.
 * 
 * @author camilo
 *
 */
public class AccessDAO {

	final static Logger log = Logger.getLogger(AccessDAO.class);
	final static ApplicationContext ctx = new ClassPathXmlApplicationContext("app-context.xml");

	/**
	 * Insert queue message in table queue_messages
	 */
	public static void persistQueue(Queue queue) {
		// save queue messages in database
		try {
			BasicDataSource ds = (BasicDataSource) ctx.getBean("mysql-ds");
			Connection conn = (Connection) ds.getConnection();
			StringBuilder sb = new StringBuilder("INSERT INTO queues");
			sb.append(" (name, state, JMSMessageID, JMSTimestamp, JMSCorrelationID, client_id, operation_type)");
			sb.append(" VALUES (?,?,?,?,?,?,?)");
			String sql = sb.toString();
			log.debug("Executing sql: "+sql);
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, queue.getName());
			stm.setString(2, String.valueOf(queue.getState()));
			stm.setString(3, queue.getjMSMessageID());
			stm.setLong(4, queue.getjMSTimestamp());
			stm.setString(5, queue.getjMSCorrelationID());
			stm.setString(6, queue.getClientID());
			stm.setString(7, String.valueOf(queue.getOperationType()));
			int result = stm.executeUpdate();
			log.info("Insert in database was: " + (result > 0 ? "success" : "failed"));
		} catch (Exception e) {
			log.error("Error, the queue could not have been saved", e);
		}
	}
}
