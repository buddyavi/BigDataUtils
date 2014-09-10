package com.usc.avi.memcache;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.spy.memcached.MemcachedClient;

import com.usc.avi.memcache.constants.Constants;
import com.usc.avi.memcache.daoimpl.MysqlToMemcache;
import com.usc.avi.memcache.util.MemcachePropertiesLoader;

/**
 * Class for populating memcache with varible to data mapping
 * 
 */

public class MemcacheMain {

	private static Logger logger = LoggerFactory.getLogger(MemcacheMain.class);
	private static Properties prop;
	static String propFile = Constants.PROPERTY_FILE_NAME;
	static {
		prop = MemcachePropertiesLoader.loadJobProps(propFile);
	}

	public static void main(String[] args) {
		MysqlToMemcache memconnector = new MysqlToMemcache();

		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap = memconnector.getColDatatype();
		Set<String> keys = dataMap.keySet();

		try {
			logger.info("Establishing memcache connection");
			MemcachedClient c = new MemcachedClient(new InetSocketAddress(
					prop.getProperty(Constants.MEMCACHE_HOST_PRIMARY),
					Integer.parseInt(prop
							.getProperty(Constants.MEMCACHE_PORT_PRIMARY))));
			if(c.getAvailableServers().isEmpty()){
				logger.error("No memcache server defined!!");
				System.exit(1);

			}
			logger.info("populating memcache");
			for (String s : keys) {
				c.set(s, 2592000, dataMap.get(s));
			}
			System.out.println("******* Columns from memcache *********");
			for (String s : keys) {

				System.out.println(s + ": " + c.get(s));

			}
			System.out.println("Updated cache" + c.getStats());
			c.shutdown();
		} catch (IOException e) {

			logger.error("IOException:" + e);
			System.exit(1);

		} catch (Exception e) {

			logger.error("Exception:" + e);
			System.exit(1);

		}
		logger.info("Memcache population Completed");
		System.out.println("Memcache Population Complete!!!!");
	}

}
