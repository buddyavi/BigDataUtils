package com.usc.avi.memcache.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MemcachePropertiesLoader {
	
	public static Properties props = new Properties();
	
	private static Logger logger = LoggerFactory.getLogger(MemcachePropertiesLoader.class);

	/**
	 * Constructor
	 */
	public MemcachePropertiesLoader() {
	}

	/**
	 * Method: loadJobProps
	 * 
	 * @param fileName
	 * @return
	 */
	public static Properties loadJobProps(String strFileName) {
        
		logger.info("Loading Properties file: " + strFileName);

		try {

			// Loading the properties file
			
			props.load(MemcachePropertiesLoader.class.getClassLoader().getResourceAsStream(strFileName));

		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
			System.exit(1);
		} catch (IOException e) {
			logger.error(e.getMessage());
			System.exit(1);
		}

		// return
		return props;
	}

	public static Properties getProps() {
		return props;
	}

	public static void setProps(Properties props) {
		MemcachePropertiesLoader.props = props;
	}
	

}
