package com.ots.dao;

import java.sql.ResultSet;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ots.beans.Oilprice;
import com.ots.util.MySqlExecute;

@Component
public class AdminDAO {
	static final Logger log = Logger.getLogger(AdminDAO.class);

	public void updateOilPrice(Oilprice oilprice) {
		String query = "update `oilprice` set `Op_Price` = "
				+ oilprice.getOilprice();
		try {
			MySqlExecute.executeUpdateMySqlQuery(query);
		} catch (Exception e) {
			log.error("Error in payment " + e);
		}
	}

	public double getOilPrice() {
		double oilPrice = 0.0;
		String query = "Select * from oilprice Order By Op_date Desc Limit 1";
		try {
			ResultSet rs = MySqlExecute.executeMySqlQuery(query);
			while (rs.next()) {
				oilPrice = rs.getDouble("Op_price");
			}
		} catch (Exception e) {
			log.error("Error in fetching oil price");
		}
		return oilPrice;
	}
}