package com.ots.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.log4j.Logger;

public class MySqlExecute {
	static final Logger log = Logger.getLogger(MySqlExecute.class);
	
	//SELECT query
	static public ResultSet executeMySqlQuery(String sql) throws Exception{
		Connection conn = null;
		Statement stmt = null;
	    ResultSet rs = null;  
		try {
			
			conn = ConnectionManager.mySqlConnectionPool.getConnection();
			
			stmt = conn.createStatement();
		    rs = stmt.executeQuery(sql);    
		    ConnectionManager.mySqlConnectionPool.free(conn);
		} catch(SQLException e) {
			log.error("Error in executeMySql query: "+e);
			throw new Exception("Error in query data");
		} catch (Exception e) {
			log.error("Error in executeMySql query: "+e);		
		}
		
		return rs;	
	}
	
	//INSERT, UPDATE, and DELETE queries
	static public int executeUpdateMySqlQuery(String sql) throws Exception{
		Connection conn = null;
		Statement stmt = null;
	    int rs = 0;  
		try {
			conn = ConnectionManager.mySqlConnectionPool.getConnection();
			
			stmt = conn.createStatement();
		    rs = stmt.executeUpdate(sql);    
		    ConnectionManager.mySqlConnectionPool.free(conn);
		} catch(SQLException e) {
			log.error("Error in executeUpdateMySql query: "+e);
			throw new Exception("Error in query data");
		} catch (Exception e) {
			log.error("Error in executeUpdateMySql query: "+e);
			}finally {			
				try {
					stmt.close();
				} catch (SQLException e) {
					log.error("executeUpdateMySqlQuery : Exception in Closing connection.",e);
				}
				
			}
		
		return rs;	
	}
	
	//Call this to close all connections
	static public void closeConnections(){
		ConnectionManager.mySqlConnectionPool.closeAllConnections();
	}

}