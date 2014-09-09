package com.usc.avi.memcache.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.usc.avi.memcache.constants.Constants;
import com.usc.avi.memcache.util.MemcachePropertiesLoader;
/**
 * CLass for extablishing MySql connection
 *
 */

public class MySqlConnection {

	private static Logger logger = LoggerFactory.getLogger(MySqlConnection.class);
	
	private static Properties prop;
	static String propFile = Constants.PROPERTY_FILE_NAME;
	static {
		prop = MemcachePropertiesLoader.loadJobProps(propFile);
	}

	static String url = "jdbc:mysql://"
			+ prop.getProperty(Constants.MYSQL_SERVER_NAME) + "/"
			+ prop.getProperty(Constants.MYSQL_DATABASE_NAME);

		public static Connection connection = null;
/**
 * Method to establish connection to mysql DB
 * @return
 */
	public static Connection getConnection() {
		logger.info("Establishing connection at: "+url);
		try {
			Class.forName(prop.getProperty(Constants.MYSQL_DRIVER_NAME));
			connection = DriverManager.getConnection(url,
					prop.getProperty(Constants.MYSQL_USER),
					prop.getProperty(Constants.MYSQL_PASSWORD));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;

	}

}
