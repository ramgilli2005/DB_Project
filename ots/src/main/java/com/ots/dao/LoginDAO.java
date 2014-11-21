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
	
	// Check User in DB
	public UserInfo checkLogin(LoginInfo details) {
		UserInfo uinfo = null;

		String query = "SELECT * from client WHERE Client_Id="
				+ details.getUserId() + " AND Client_pwd=" + details.getPwd();
		
		try{
			ResultSet rs = MySqlExecute.executeMySqlQuery(query);
			if(rs != null){
				uinfo = new UserInfo();
				while(rs.next()){
					uinfo.setClientId(rs.getString("Client_Id"));
					uinfo.setfName(rs.getString("Client_fname"));
					uinfo.setlName(rs.getString("Client_lname"));
					uinfo.setLvl(rs.getString("Client_level"));
				}
			}
			
		} catch(Exception e){
			log.error("Error in Login Check: "+e);
		}
		
		return uinfo;
	}
}
