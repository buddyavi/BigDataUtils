package com.usc.avi.memcache.constants;

public class Constants {
	
	//Memcache connection constants 
	
	public static final String MEMCACHE_HOST_PRIMARY = "primary_host";
	public static final String MEMCACHE_PORT_PRIMARY= "primary_port";
	
	//MySql connection constants
	
	public static final String MYSQL_DRIVER_NAME="mysql_driver_name";
	public static final String MYSQL_SERVER_NAME="mysql_server_name";
	public static final String MYSQL_DATABASE_NAME="mysql_database_name";
	public static final String MYSQL_USER="mysql_user";
	public static final String MYSQL_PASSWORD="mysql_password";
	
	// MySql sample query to retrieve key , value pairs
	public static final String QUERY_TO_GET_MAPPING="select NAME,STUDENT_ID from students";
	
	//property file name
	public static final String  PROPERTY_FILE_NAME="memcache.properties";
}
