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
	public void makeTxn (Txn txn) {

		String query = "insert into transaction (`Txn_quantity`, `Txn_type`, `Txn_comsn_type`,"
					+ "`Txn_Client_Id`, `Txn_total_comsn`, `Txn_cost`) values"
					+ "("+txn.getQuantity()+",'"+txn.getType()+"','"+txn.getComsnType()+"','"
					+txn.getClientId()+"',"+txn.getTotalComsn()+","+txn.getTxnCost()+")";

		try {
			MySqlExecute.executeUpdateMySqlQuery(query);
		} 
		catch (Exception e) {
			log.error("Error in creating transation " + e);
		}
	}
	
	//Can use the method view oil cash reserves. check in controller itself whether the amount n 
	//clientdbo is greater than the amount of oil to be sold
	
	//Check if oil reserves are sufficient
	/*public boolean CheckOilReserves(ClientDbo cdbo) {
		//String query = "SELECT `Clientdbo_quantity` FROM `clientdbo` WHERE clientdbo_id = '" + + "';";
		try {
			ResultSet rs = MySqlExecute.executeMySqlQuery(query);
			if (cdbo.getQuantiy() > txnamnt) {
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
	}*/
	
	//Make payment for transaction
	public void Payment(PaymentForTxn payment){
		String query = "insert into `payment_info` (`Payment_amount`,`Payment_txn_id`)"
				+ " values ("+payment.getPaymentAmount()+","+payment.getTxnId()+",'" +payment.getClientId()+ ");";
		
		try{
			MySqlExecute.executeUpdateMySqlQuery(query);
		}
		catch(Exception e){
			log.error("Error in payment "+e);
		}
		
		query = "update `Transaction` set `Txn_Cost_Paid` = "+payment.getRemainingBalance()+"where `txn_id` = "+payment.getTxnId() + ";";
		
		try{
			MySqlExecute.executeUpdateMySqlQuery(query);
		}
		catch(Exception e){
			log.error("Error in deducting cost paid in transaction after payment "+e);
		}
	}
	
	//View pending transactions
	public List<Txn> ViewPendingTxn(String clientId){
		String query = "select * from transaction where `Txn_Client_Id` = " + clientId + ";";
		try{
			ResultSet rs = MySqlExecute.executeMySqlQuery(query);
			if(rs.next()){
				Txn txn = new Txn();
				List<Txn> txns= new ArrayList<Txn>();
				while(rs.next()){
					txn.setTxnId(rs.getInt(1));
					txn.setTxnDate(rs.getDate(2));
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
			else
				return null;
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
		
		query = "delete from payment_info where payment_txn_id = " + txninfo.getTxnId() + ""
				+ "and payment_client_id = '" + txninfo.getClientId()+ "';";
		try{
		MySqlExecute.executeUpdateMySqlQuery(query);
		}
		catch(Exception e){
			log.error("Error in deleting payment for that transaction " + e);
		}
		
		query = "update clientdbo set clientdbo_quantity = " + txninfo.getTxnamnt()
				+ "where clientdbo_id = '" + txninfo.getClientId() + "';";
		try{
			MySqlExecute.executeUpdateMySqlQuery(query);
		}
		catch(Exception e){
			log.error("Error in updating the value of transaction amount in transactio table" + e);
		}		
	}
	
	//Insert into transaction log after deleting from transaction
	public void InsertIntoTxnLog(TxnLog txnlog){
		String query = "insert into transaction_log (`Txn_Log_Id`,`Txn_Log_Client_Id`,`Txn_Log_Date`,`Txn_Log_Quantity`, `Txn_Log_Type`,"
				+ " `Txn_Log_Commission_Type`, `Txn_Log_status`, `Txn_log_Trader_id`, `Txn_log_cost`, `Txn_log_total_comsn`) "
				+ "values (" + txnlog.getTxnLogId() + ",'" +txnlog.getClientId() + "'," + txnlog.getTxnDate() + ","
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
			PaymentForTxn payment = new PaymentForTxn();
			ResultSet rs = MySqlExecute.executeMySqlQuery(query);
			if(rs.next()){
				while(rs.next()){
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
				TxnLog txnlog = new TxnLog();
				while(rs.next()){
					txnlog.setTxnLogId(rs.getInt("txn_log_id"));
					txnlog.setClientId(rs.getString("txn_log_client_id"));
					txnlog.setTxnDate(rs.getDate("txn_log_date"));
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
	public List<ClientDbo> ViewOilCashReserves(String clientId){
		String query = "select * from clientdbo where `clientdbo_id` = '"+clientId+"';";
		try{
			ResultSet rs = MySqlExecute.executeMySqlQuery(query);
			if(rs.next()){
				ClientDbo cdbo = new ClientDbo();
				List<ClientDbo> cdbos = new ArrayList<ClientDbo>();
				while(rs.next()){
					cdbo.setClientId(clientId);
					cdbo.setQuantiy(rs.getDouble("Clientdbo_quantity"));
					cdbo.setCredit(rs.getDouble("Clientdbo_credit"));
					cdbos.add(cdbo);
				}
				return cdbos;
			}
			else
				return null;
		}
		catch(Exception e){
			log.error("Error in fetching oil and cash reserves "+e);
			return null;
		}
	}
}