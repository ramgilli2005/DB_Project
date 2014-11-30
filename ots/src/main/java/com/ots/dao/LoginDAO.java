package com.ots.dao;

import java.sql.ResultSet;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ots.beans.LoginInfo;
import com.ots.beans.UserInfo;
import com.ots.util.MySqlExecute;

@Component
public class LoginDAO {

	static final Logger log = Logger.getLogger(LoginDAO.class);
	private static final String USER_TYPE_CUSTOMER = "customer";

	// Check User in DB
	public UserInfo checkLogin(LoginInfo details) {
		UserInfo uinfo = null;

		String query = "SELECT * from client WHERE Client_Id='"
				+ details.getUserId() + "' AND Client_pwd='" + details.getPwd()
				+ "'";

		try {
			ResultSet rs = MySqlExecute.executeMySqlQuery(query);
			while (rs.next()) {
				uinfo = new UserInfo();
				log.debug("Client ID: " + rs.getString("Client_Id"));
				uinfo.setClientId(rs.getString("Client_Id"));
				uinfo.setfName(rs.getString("Client_fname"));
				uinfo.setlName(rs.getString("Client_lname"));
				uinfo.setLvl(rs.getString("Client_level"));
				uinfo.setUserType(USER_TYPE_CUSTOMER);
			}

		} catch (Exception e) {
			log.error("Error in Login Check: " + e);
		}

		return uinfo;
	}
	
	public UserInfo checkEmplLogin(LoginInfo details){
		UserInfo uInfo = null;
		String query = "SELECT * from system WHERE Sys_Id='"+details.getUserId()+"' "
				+ "AND Sys_password='"+details.getPwd()+"'";
		try{
			ResultSet rs = MySqlExecute.executeMySqlQuery(query);
			while(rs.next()){
				uInfo = new UserInfo();
				log.debug("System_Id: "+rs.getString("Sys_Id"));
				uInfo.setClientId(rs.getString("Sys_Id"));
				uInfo.setfName(rs.getString("Sys_fname"));
				uInfo.setlName(rs.getString("Sys_lname"));
				uInfo.setUserType(rs.getString("Sys_position"));
			}
		} catch(Exception e){
			log.error("Error in checking admin details");
		}
		return uInfo;
	}
}
