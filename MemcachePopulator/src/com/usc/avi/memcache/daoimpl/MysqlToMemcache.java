package com.usc.avi.memcache.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.usc.avi.memcache.constants.Constants;
import com.usc.avi.memcache.mysql.MySqlConnection;

/**
 * Class for getting column name to value mapping from mySql db
 * 
 */	
public class MysqlToMemcache {
	private static Logger logger = LoggerFactory
			.getLogger(MysqlToMemcache.class);
	Connection connection = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Statement stmt = null;

	/**
	 * Method to get column name to value mapping from mysql DB
	 * 
	 * @return map
	 */
	public Map<String, String> getColDatatype() {
		logger.info("inside getColDatatype() method");

		Map<String, String> dataMap = new HashMap<String, String>();
		try {
			connection = MySqlConnection.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(Constants.QUERY_TO_GET_MAPPING);
			while (rs.next()) {

				String column1 = rs.getString("NAME").trim();
				String column2 = rs.getString("STUDENT_ID").trim();
				dataMap.put(column1, column2);
			}

		} catch (SQLException e) {

			logger.error("SQLException:" + e);
			System.exit(1);

		} catch (Exception e) {
			logger.error("Exception:" + e);
			System.exit(1);

		}
		logger.info("Column name to value map size: " + dataMap.size());
		return dataMap;

	}

}
