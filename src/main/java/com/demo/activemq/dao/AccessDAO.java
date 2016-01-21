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
			String sql = "INSERT INTO queue_messages(queue_name,message,state) VALUES (?,?,?);";
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, queue.getName());
			stm.setString(2, queue.getMessage());
			stm.setString(3, queue.getState());
			int result = stm.executeUpdate();
			log.info("Insert in database was: " + (result > 0 ? "success" : "failed"));
		} catch (Exception e) {
			log.error("Error, the queue could not have been saved", e);
		}
	}
}
