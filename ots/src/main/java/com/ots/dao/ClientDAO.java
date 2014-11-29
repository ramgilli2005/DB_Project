package com.ots.dao;

import java.sql.ResultSet;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ots.beans.MakeTxn;
import com.ots.beans.PaymentForTxn;
import com.ots.beans.UserInfo;
import com.ots.util.MySqlExecute;

@Component
public class ClientDAO {

	static final Logger log = Logger.getLogger(ClientDAO.class);

	// Initiate transaction
	public void makeTxn (MakeTxn txn) {

		String query = "insert into transaction (`Txn_quantity`, `Txn_type`, `Txn_comsn_type`, `Txn_comsn_paid`, "
				+ "`Txn_Client_Id`, `Txn_total_comsn`, `Txn_cost`, `Txn_cost_paid`) values"
				+ "("+txn.getQuantity()+",'"+txn.getType()+"','"+txn.getComsnType()+"',"+txn.getComnsPaid()+",'"
				+txn.getClientId()+"',"+txn.getTotalComns()+","+txn.getTxnCost()+","+txn.getComnsPaid()+")";

		try {
			MySqlExecute.executeUpdateMySqlQuery(query);
			
		} 
		catch (Exception e) {
			log.error("Error in creating transation " + e);
		}
	}
	
	//Check if oil reserves are sufficient
	public boolean CheckOilReserves(double txnamnt) {
		String query = "SELECT `Clientdbo_quantity` FROM `clientdbo` WHERE clientdbo_id = '"+"';";
		try {
			ResultSet rs = MySqlExecute.executeMySqlQuery(query);
			if (rs.getDouble(1) > txnamnt) {
				log.debug("Oil reserves = "+rs.getDouble(1));
				return true;
			}
			else
				return false;
		} 
		catch (Exception e) {
			log.error("Error in checking oil reserves "+e);
			return false;
		}
	}
	
	//Make payment for transaction
	public void Payment(PaymentForTxn payment){
		String query = "insert into `payment_info` (`Payment_amount`,`Payment_txn_id`)"
				+ " values ("+payment.getPaymentAmount()+",'"+payment.getTxnId()+"','" +payment.getClientId()+ ");";
		
		try{
			MySqlExecute.executeUpdateMySqlQuery(query);
		}
		catch(Exception e){
			log.error("Error in payment "+e);
		}
	}
	
	//View pending transactions
	public ResultSet ViewPendingTxn(String clientId){
		String query = "select * from transaction where `Txn_Client_Id` = " + clientId + ";";
		try{
			ResultSet rs = MySqlExecute.executeMySqlQuery(query);
			return rs;
		}
		catch(Exception e){
			log.error("Error in fetching pending transactions "+e);
			return null;
		}
	}
	
	//View all past transactions
	public void ViewTxns(){
		String query = 
		try{
			
		}
		catch(Exception e){
			log.error("Error in fetching transactions "+e);
		}
	}
	
	//View oil and cash reserves
	public void ViewOilCashReserves(){
		String query = 
		try{
			
		}
		catch(Exception e){
			log.error("Error in fetching oil and cash reserves "+e);
		}
	}
}
