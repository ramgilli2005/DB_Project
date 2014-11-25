package com.ots.util;

import java.sql.SQLException;
import org.apache.log4j.Logger;

public class ConnectionManager {
	static final Logger log = Logger.getLogger(ConnectionManager.class);
	public static final ConnectionPool mySqlConnectionPool = getMySqlConnectionPool();
	
	static private ConnectionPool getMySqlConnectionPool() {
		ConnectionPool cp = null;
		try {
			log.debug("Config Manager Test: "+ ConfigManager.getString("database.connection.driver"));
			cp = new ConnectionPool(ConfigManager.getString("database.connection.driver"),
					ConfigManager.getString("database.connection.string"),
					ConfigManager.getString("database.username"),
					ConfigManager.getString("database.password"), 4, 20,
					true);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error("Error in creating Connection: "+e);
		}
		return cp;
	}
}