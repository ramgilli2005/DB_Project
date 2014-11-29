package com.ots.dao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ots.beans.CreditCard;
import com.ots.util.MySqlExecute;

@Component
public class CardDetailsDAO {

	static final Logger log = Logger.getLogger(CardDetailsDAO.class);

	 
	public boolean insertCardDetails(CreditCard card) {
		String query = "insert into card_details (`Card_number`, `Card_expiry_date`"
				+ ", `Card_CVV`, `Card_Client_Id`, `Card_client_name`) values "
				+ "('"+card.getCardNo()+"', '"+card.getExpYr()+"/"+card.getExpMon()+"', "
				+ "'"+card.getCvv()+"', '"+card.getClientId()+"', '"+card.getCardName()+"')";
		try {
			MySqlExecute.executeUpdateMySqlQuery(query);
			return true;
		} catch (Exception e) {
			log.error("Error in Login Check: " + e);
		}
		return false;
	}
}
