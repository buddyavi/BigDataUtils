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
			logger.error("ClassNotFoundException:"+e);
			System.exit(1);
		} catch (SQLException e) {
			logger.error("SQLException:"+e);
			System.exit(1);

		}
		catch (Exception e) {
			logger.error("Exception:"+e);
			System.exit(1);

		}
		return connection;

	}

}
