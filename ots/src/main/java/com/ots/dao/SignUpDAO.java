package com.ots.dao;

import java.sql.ResultSet;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ots.beans.UserInfo;
import com.ots.util.MySqlExecute;

@Component
public class SignUpDAO {

	static final Logger log = Logger.getLogger(SignUpDAO.class);

	// Check User in DB
	public boolean checkUserExists(String ssn) {

		String query = "SELECT * from client WHERE Client_SSN='" + ssn + "'";

		try {
			ResultSet rs = MySqlExecute.executeMySqlQuery(query);
			while (rs.next()) {
				log.debug("Client ID: " + rs.getString("Client_Id"));
				String clientId = rs.getString("Client_Id");
				if (clientId != null) {
					return true;
				}

			}

		} catch (Exception e) {
			log.error("Error in Login Check: " + e);
		}

		return false;
	}

	public String createClientId(String prefix) {
		String query = "select substring(client_id,4) AS Client_Id from client order by creation_date desc Limit 1";
		try {
			ResultSet rs = MySqlExecute.executeMySqlQuery(query);
			int id = 100000;
			while (rs.next()) {
				log.debug("Max Client ID: " + rs.getString("Client_Id"));
				id = Integer.parseInt(rs.getString("Client_Id"));
			}
			id++;
			return prefix + id;
		} catch (Exception e) {
			log.error("Error in creating clientId");
		}

		return null;
	}

	public String insertUserDetails(UserInfo uInfo) {
		StringBuilder clIdprefix = new StringBuilder();
		clIdprefix.append(uInfo.getfName().substring(0, 1));
		clIdprefix.append("x");
		clIdprefix.append(uInfo.getlName().substring(0, 1));
		String clientId = createClientId(clIdprefix.toString());
		log.debug("Client Id:" + clientId);
		String query = "insert into client(`Client_Id`, `Client_pwd`, `Client_SSN`, "
				+ "`Client_fname`, `Client_lname`, `Client_mobile`, `Client_phno`, `Client_level`, "
				+ "`Client_email`) values ('"+clientId+"', '"+uInfo.getPassword()+"', "
						+ "'"+uInfo.getSsn()+"', '"+uInfo.getfName()+"', "
				+ "'"+uInfo.getlName()+"', '"+uInfo.getMobNo()+"', '"+uInfo.getPhoneNo()+"', "
						+ "'SILVER', '"+uInfo.getEmail()+"')";
		String addressQuery = "insert into address(`Address_street`,`Address_City`,`Address_State`,"
								+ "`Address_ZIP`,`Address_Client_Id`)"
								+"values('"+uInfo.getAddress()+"','"+uInfo.getCity()+"','"+uInfo.getState()+"',"
								+ "'"+uInfo.getZip()+"','"+clientId+"')";
		try{
			MySqlExecute.executeUpdateMySqlQuery(query);
			MySqlExecute.executeUpdateMySqlQuery(addressQuery);
			return clientId;
		} catch(Exception e){
			log.error("Error in inserting Client Details");
		}
		return null;
	}
}
