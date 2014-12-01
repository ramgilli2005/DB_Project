package com.ots.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ots.beans.ClientDbo;
import com.ots.beans.PaymentForTxn;
import com.ots.beans.Txn;
import com.ots.beans.TxnInfo;
import com.ots.beans.TxnLog;
import com.ots.util.MySqlExecute;

@Component
public class ClientDAO {

	static final Logger log = Logger.getLogger(ClientDAO.class);

	//Initiate transaction
	public int makeTxn (Txn txn) {
		int txnId = -1;
		String query = "insert into transaction (`Txn_quantity`, `Txn_type`, `Txn_comsn_type`,"
					+ "`Txn_Client_Id`, `Txn_total_comsn`, `Txn_cost`, `Txn_cost_paid`) values"
					+ "("+txn.getQuantity()+",'"+txn.getType()+"','"+txn.getComsnType()+"','"
					+txn.getClientId()+"',"+txn.getTotalComsn()+","+txn.getTxnCost()+", 0.0)";
		log.debug("Txn Query: "+query);
		String txnIdQuery = "Select LAST_INSERT_ID()";
		try {
			MySqlExecute.executeUpdateMySqlQuery(query);
			ResultSet rs = MySqlExecute.executeMySqlQuery(txnIdQuery);
			
			while(rs.next()){
				txnId = rs.getInt(1);
			}
			return txnId;
		} 
		catch (Exception e) {
			log.error("Error in creating transation " + e);
		}
		return txnId;
	}
	
	// Check if oil reserves are sufficient
	public boolean CheckOilReserves(String clientId, double txnquantity) {
		String query = "SELECT `Clientdbo_quantity` FROM `clientdbo` WHERE clientdbo_id = '"
				+ clientId + "';";
		ClientDbo cdbo = ViewOilCashReserves(clientId);
		try {
			ResultSet rs = MySqlExecute.executeMySqlQuery(query);
			if (cdbo.getQuantiy() > txnquantity) {
				log.debug("Oil reserves = " + rs.getDouble(1));
				return true;
			} else
				return false;
		} catch (Exception e) {
			log.error("Error in checking oil reserves " + e);
			return false;
		}
	}
	
	//Make payment for transaction
	public void Payment(PaymentForTxn payment){
		double costPaid = 0.0;
		String query = "insert into `payment_info` (`Payment_amount`,`Payment_txn_id`, `Payment_Client_Id`)"
				+ " values ("+payment.getPaymentAmount()+","+payment.getTxnId()+",'" +payment.getClientId()+ "');";
		
		try{
			MySqlExecute.executeUpdateMySqlQuery(query);
		}
		catch(Exception e){
			log.error("Error in payment "+e);
		}
		
		query = "select txn_cost_paid from transaction where txn_Id = " + payment.getTxnId();
		
		try{
			ResultSet rs = MySqlExecute.executeMySqlQuery(query);
			while(rs.next()){
				costPaid = rs.getDouble(1);
			}
		}
		catch(Exception e){
			log.error("Error while fetching cost paid for transaction" + e);
		}
		
		costPaid += payment.getPaymentAmount();
		query = "update `Transaction` set `Txn_Cost_Paid` = " + costPaid + "where `txn_id` = "+payment.getTxnId() + ";";
		
		try{
			MySqlExecute.executeUpdateMySqlQuery(query);
		}
		catch(Exception e){
			log.error("Error in deducting cost paid in transaction after payment "+e);
		}
	}
	
	// Payment using credit balance of client in OTS
	public boolean PayUsingCredit(double creditAmount, PaymentForTxn payment) {
		double costPaid = 0.0;
		double balanceCredit = creditAmount - payment.getPaymentAmount();
		if(balanceCredit >= 0){
			String query = "update clientDbo set clientdbo_credit = "
					+ balanceCredit + " where clientdbo_Id = " + payment.getClientId();
			try {
				MySqlExecute.executeUpdateMySqlQuery(query);
			} catch (Exception e) {
				log.error("Error occurred during payment from credit" + e);
			}
			
			query = "insert into `payment_info` (`Payment_amount`,`Payment_txn_id`, `Payment_Client_Id`)"
					+ " values ("+payment.getPaymentAmount()+","+payment.getTxnId()+",'" +payment.getClientId()+ "');";
			
			try{
				MySqlExecute.executeUpdateMySqlQuery(query);
			}
			catch(Exception e){
				log.error("Error in payment "+e);
			}
			
			query = "select txn_cost_paid from transaction where txn_Id = " + payment.getTxnId();
			
			try{
				ResultSet rs = MySqlExecute.executeMySqlQuery(query);
				while(rs.next()){
					costPaid = rs.getDouble(1);
				}
			}
			catch(Exception e){
				log.error("Error while fetching cost paid for transaction" + e);
			}
			
			costPaid += payment.getPaymentAmount();
			query = "update `Transaction` set `Txn_Cost_Paid` = " + costPaid + "where `txn_id` = "+payment.getTxnId() + ";";
			
			try{
				MySqlExecute.executeUpdateMySqlQuery(query);
			}
			catch(Exception e){
				log.error("Error in deducting cost paid in transaction after payment "+e);
			}
			
			return true;

		} else {
			return false;
			
		}
		
	}
	
	
	//View pending transactions
	public List<Txn> ViewPendingTxn(String clientId){
		String query = "select * from transaction where `Txn_Client_Id` = '" + clientId + "';";
		log.debug("Query: "+query);
		try {
			ResultSet rs = MySqlExecute.executeMySqlQuery(query);
			Txn txn;
			List<Txn> txns = new ArrayList<Txn>();
			while (rs.next()) {
				txn = new Txn();
				txn.setTxnId(rs.getInt(1));
				txn.setTxnDate(rs.getTimestamp(2));
				txn.setQuantity(rs.getDouble(3));
				txn.setType(rs.getString(4));
				txn.setComsnType(rs.getString(5));
				txn.setClientId(rs.getString(6));
				txn.setTotalComsn(rs.getDouble(7));
				txn.setTxnCost(rs.getDouble(8));
				txn.setTxnCostPaid(rs.getDouble(9));
				txns.add(txn);
			}
			return txns;
		}
		catch(Exception e){
			log.error("Error in fetching pending transactions "+e);
			return null;
		}
	}
	
	//delete pending transactions and their info from payment table. Update client credit. Insert into transaction log table.
	public void DeleteTxn(TxnInfo txninfo){
		String query = "delete from transaction where `txn_id` = " + txninfo.getTxnId() + ";";
		
		try{
		MySqlExecute.executeUpdateMySqlQuery(query);
		}
		catch(Exception e){
			log.error("Error in deleting transaction " + e);
		}
		
		query = "delete from payment_info where payment_txn_id = " + txninfo.getTxnId() 
				+ " and payment_client_id = '" + txninfo.getClientId()+ "';";
		log.debug("Delete query:"+query);
		try{
		MySqlExecute.executeUpdateMySqlQuery(query);
		}
		catch(Exception e){
			log.error("Error in deleting payment for that transaction " + e);
		}
		
		query = "update clientdbo set clientdbo_quantity = " + txninfo.getQty()
				+ " , clientdbo_credit= "+txninfo.getCreditAmt()+" where clientdbo_id = '" 
				+ txninfo.getClientId() + "';";
		try{
			MySqlExecute.executeUpdateMySqlQuery(query);
		}
		catch(Exception e){
			log.error("Error in updating the value of transaction amount in transactio table" + e);
		}		
	}
	
	//Insert into transaction log after deleting from transaction
	public void InsertIntoTxnLog(TxnLog txnlog){
		String query = "insert into transaction_log (`Txn_Log_Client_Id`,`Txn_Log_Date`,`Txn_Log_Quantity`, `Txn_Log_Type`,"
				+ " `Txn_Log_Commission_Type`, `Txn_Log_status`, `Txn_log_Trader_id`, `Txn_log_cost`, `Txn_log_total_comsn`) "
				+ "values ('" +txnlog.getClientId() + "','" + txnlog.getTxnDate() + "', "
				+txnlog.getQuantity() + ",'" + txnlog.getTxnType() + "','" + txnlog.getComsnType() + "','"	
				+txnlog.getLogStatus() + "','" + txnlog.getTraderId()+ "'," +txnlog.getTxnCost() +","+ txnlog.getComsnCost() +")";
		
		try{
			MySqlExecute.executeUpdateMySqlQuery(query);
		}
		catch(Exception e){
			log.error("Error while inserting record into transaction_log " + e);
		}
	}
	
	//View payments done for a transaction of a client
	public List<PaymentForTxn> ViewPayment(TxnInfo txninfo){
		String query = "select * from Payment_info where payment_txn_id = " + txninfo.getTxnId()+ " "
				+ "and payment client_id = '" + txninfo.getClientId() +"';";
		try{
			List<PaymentForTxn> payments = new ArrayList<PaymentForTxn>();
			PaymentForTxn payment;
			ResultSet rs = MySqlExecute.executeMySqlQuery(query);
			if(rs.next()){
				while(rs.next()){
					payment = new PaymentForTxn();
					payment.setPaymentId(rs.getInt(1));
					payment.setPaymentDate(rs.getDate(2));
					payment.setPaymentAmount(rs.getDouble(3));
					payment.setTxnId(rs.getInt(4));
					payment.setStatus(rs.getString(5));
					payment.setTraderId(rs.getString(6));
					payment.setClientId(rs.getString(7));
					payments.add(payment);
				}
				return payments;
			}
			else
				return null;
		}
		catch(Exception e){
			log.error("Error in fetching data from payment info " + e);
			return null;
		}
	}
	
	//View all past transactions
	public List<TxnLog> ViewTxns(String clientId){
		String query = "select * from transaction_log where `txn_log_client_id` = '" + clientId + "';";
		try{
			ResultSet rs = MySqlExecute.executeMySqlQuery(query);
			if(rs.next()){
				List<TxnLog> txnlogs = new ArrayList<TxnLog>();
				TxnLog txnlog;
				while(rs.next()){
					txnlog = new TxnLog();
					txnlog.setTxnLogId(rs.getInt("txn_log_id"));
					txnlog.setClientId(rs.getString("txn_log_client_id"));
					txnlog.setTxnDate(rs.getTimestamp("txn_log_date"));
					txnlog.setQuantity(rs.getDouble("txn_log_quantity"));
					txnlog.setTxnType(rs.getString("txn_log_type"));
					txnlog.setComsnType(rs.getString("txn_log_commission_type"));
					txnlog.setLogStatus(rs.getString("txn_log_status"));
					txnlog.setTraderId(rs.getString("txn_log_Trader_id"));
					txnlog.setTxnCost(rs.getDouble("txn_log_cost"));
					txnlog.setComsnCost(rs.getDouble("txn_log_total_comsn"));
					txnlogs.add(txnlog);
				}
				return txnlogs;
			}
			else
				return null;
		}
		catch(Exception e){
			log.error("Error in fetching transactions "+e);
			return null;
		}
	}
	
	//View oil and cash reserves
	public ClientDbo ViewOilCashReserves(String clientId){
		String query = "select * from clientdbo where `clientdbo_id` = '"+clientId+"';";
		try{
			log.debug("ClientId: "+clientId);
			ResultSet rs = MySqlExecute.executeMySqlQuery(query);
			ClientDbo cdbo = new ClientDbo();
			while(rs.next()){
					cdbo.setClientId(clientId);
					cdbo.setQuantiy(rs.getDouble("Clientdbo_quantity"));
					cdbo.setCredit(rs.getDouble("Clientdbo_credit"));
					return cdbo;
			}
			log.debug("Returning Null");
			return null;
		}
		catch(Exception e){
			log.error("Error in fetching oil and cash reserves "+e);
			return null;
		}
	}
	
	public double getOilPrice(){
		double oilPrice = 0.0; 
		String query = "Select * from oilprice Order By Op_date Desc Limit 1";
		try{
			ResultSet rs = MySqlExecute.executeMySqlQuery(query);
			while(rs.next()){
				oilPrice = rs.getDouble("Op_price");
			}
		} catch(Exception e){
			log.error("Error in fetching oil price");
		}
		return oilPrice;
	}
	
	public Txn getClientTxn(int txnId, String clientId){
		Txn txn;
		String query = "Select * from transaction where Txn_Id="+txnId+ " and Txn_Client_Id='"+clientId+"'";
		
		try{
			ResultSet rs = MySqlExecute.executeMySqlQuery(query);
			while(rs.next()){
				txn = new Txn();
				txn.setTxnId(rs.getInt("Txn_Id"));
				txn.setTxnDate(rs.getTimestamp("Txn_date"));
				txn.setQuantity(rs.getDouble("Txn_quantity"));
				txn.setType(rs.getString("Txn_type"));
				txn.setComsnType(rs.getString("Txn_comsn_type"));
				txn.setClientId(rs.getString("Txn_Client_Id"));
				txn.setTotalComsn(rs.getDouble("Txn_total_comsn"));
				txn.setTxnCost(rs.getDouble("Txn_cost"));
				txn.setTxnCostPaid(rs.getDouble("Txn_cost_paid"));
				txn.setTraderId(rs.getString("Txn_Trader_Id"));
				
				return txn;
			}
		} catch(Exception e){
			log.error("Error in fetching txn details");
		}
		return null;
	}
	public boolean cancelTxn(List<Integer> txnIdList, String clientId, String cancellerId){
		boolean b = false;
		try{
			//Cancel txn from Transaction table and if txn_paid > 0 credit it to the client
			for(Integer txnId : txnIdList){
				Txn txn = getClientTxn(txnId, clientId);
				TxnInfo txnInfo = new TxnInfo();
				txnInfo.setTxnId(txn.getTxnId());
				txnInfo.setClientId(txn.getClientId());
				ClientDbo cdbo = ViewOilCashReserves(clientId);
				
				//Provide the updated quantity
				double qty = cdbo.getQuantiy() - txn.getQuantity();
				txnInfo.setQty(qty);
				
				double creditAmt = cdbo.getCredit() + txn.getTxnCostPaid();
				txnInfo.setCreditAmt(creditAmt);
				
				DeleteTxn(txnInfo);
				
				TxnLog txnLog = new TxnLog();
				txnLog.setClientId(txn.getClientId());
				txnLog.setComsnCost(txn.getTotalComsn());
				txnLog.setComsnType(txn.getComsnType());
				txnLog.setLogStatus("CANCELLED");
				txnLog.setQuantity(txn.getQuantity());
				txnLog.setTraderId(cancellerId);
				txnLog.setTxnCost(txn.getTxnCost());
				txnLog.setTxnType(txn.getType());
				txnLog.setTxnDate(txn.getTxnDate());
				InsertIntoTxnLog(txnLog);
			}
			
		} catch(Exception e){
			
		}
		
		return b;
	}
}