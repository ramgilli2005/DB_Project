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
import com.ots.beans.UserInfo;
import com.ots.util.MySqlExecute;

@Component
public class TraderDAO {
	
	static final Logger log = Logger.getLogger(TraderDAO.class);

	//Initiate transaction for a client.
	public int makeTxn (Txn txn) {
		int txnId = -1;

		String query = "insert into transaction (`Txn_quantity`, `Txn_type`, `Txn_comsn_type`,"
					+ "`Txn_Client_Id`, `Txn_total_comsn`, `Txn_cost`, `Txn_Trader_Id`) values"
					+ "("+txn.getQuantity()+",'"+txn.getType()+"','"+txn.getComsnType()+"','"
					+txn.getClientId()+"',"+txn.getTotalComsn()+","+txn.getTxnCost()+ ",'"+txn.getTraderId()+"')";
		String txnIdQuery = "Select LAST_INSERT_ID()";
		
		try {
			MySqlExecute.executeUpdateMySqlQuery(query);
			
			ResultSet rs = MySqlExecute.executeMySqlQuery(txnIdQuery);
			
			while(rs.next()){
				txnId = rs.getInt(1);
			}
			
			if(txn.getType().equals("SELL")){
				ClientDbo cdbo = ViewOilCashReserves(txn.getClientId());
				double qty = cdbo.getQuantiy() - txn.getQuantity();
				String sellUpdateOil = "update clientdbo set ClientDbo_quantity="+ qty
						+" where clientdbo_id="+txn.getClientId();
				MySqlExecute.executeUpdateMySqlQuery(sellUpdateOil);
			}
		} 
		catch (Exception e) {
			log.error("Error in creating transation for client " + txn.getClientId() + " " + e);
		}
		return txnId;
	}
	
	//View pending transactions
	public List<Txn> ViewPaidPendingTxn(){
		String query = "select * from transaction WHERE `Txn_cost`=`Txn_cost_paid`";
		log.debug("Query: "+query);
		try{
			ResultSet rs = MySqlExecute.executeMySqlQuery(query);
				List<Txn> txns= new ArrayList<Txn>();
				while(rs.next()){
					Txn txn = new Txn();
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
	
	public List<Txn> ViewPartialPaidPendingTxn(){
		String query = "select * from transaction WHERE `Txn_cost`<>`Txn_cost_paid`";
		try{
			ResultSet rs = MySqlExecute.executeMySqlQuery(query);

				List<Txn> txns= new ArrayList<Txn>();
				while(rs.next()){
					Txn txn = new Txn();
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

	//delete pending transactions and their info from payment table. Update client credit.
	public void DeleteTxn(TxnInfo txninfo){
		String query = "delete from transaction where `txn_id` = " + txninfo.getTxnId() + ";";
		
		try{
		MySqlExecute.executeUpdateMySqlQuery(query);
		}
		catch(Exception e){
			log.error("Error in deleting transaction " + e);
		}
		
		query = "delete from payment_info where payment_txn_id = " + txninfo.getTxnId() + ""
				+ "and payment_client_id = '"+txninfo.getClientId()+"';";
		try{
			MySqlExecute.executeUpdateMySqlQuery(query);
		}
		catch(Exception e){
			log.error("Error in deleting payment for that transaction " + e);
		}
		
		query = "update clientdbo set clientdbo_quantity = " + txninfo.getQty()
				+ " where clientdbo_id = '"+txninfo.getClientId()+"';";
		
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
				+txnlog.getLogStatus() + "','" + txnlog.getTraderId()+ "'," +txnlog.getTxnCost() +",'"+ txnlog.getComsnCost() +"')";
		
		try{
			MySqlExecute.executeUpdateMySqlQuery(query);
		}
		catch(Exception e){
			log.error("Error while inserting record into transaction_log " + e);
		}
	}
	
	//View all past transactions of all clients
	public List<TxnLog> ViewTxns(){
		String query = "select * from transaction_log";
		try{
			ResultSet rs = MySqlExecute.executeMySqlQuery(query);
				List<TxnLog> txnlogs = new ArrayList<TxnLog>();
				while(rs.next()){
					TxnLog txnlog = new TxnLog();
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
		catch(Exception e){
			log.error("Error in fetching transactions "+e);
			return null;
		}
	}
	
	//View oil and cash reserves
	public ClientDbo ViewOilCashReserves(String clientId){
		String query = "select * from clientdbo where `clientdbo_id` = '"+clientId+"';";
		try{
			ResultSet rs = MySqlExecute.executeMySqlQuery(query);
				ClientDbo cdbo = new ClientDbo();
				while(rs.next()){
					
					cdbo.setClientId(clientId);
					cdbo.setQuantiy(rs.getDouble("Clientdbo_quantity"));
					cdbo.setCredit(rs.getDouble("Clientdbo_credit"));
				}
				return cdbo;
		}
		catch(Exception e){
			log.error("Error in fetching oil and cash reserves "+e);
			return null;
		}
	}
	
	public Txn getClientTxn(int txnId){
		Txn txn;
		String query = "Select * from transaction where Txn_Id="+txnId;
		
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
	
	public boolean cancelTxn(List<Integer> txnIdList, String traderId){
		boolean b = false;
		try{
			//Cancel txn from Transaction table and if txn_paid > 0 credit it to the client
			for(Integer txnId : txnIdList){
				Txn txn = getClientTxn(txnId);
				TxnInfo txnInfo = new TxnInfo();
				txnInfo.setTxnId(txn.getTxnId());
				txnInfo.setClientId(txn.getClientId());
				ClientDbo cdbo = ViewOilCashReserves(txn.getClientId());
				
				//Provide the updated quantity
				if(txn.getType() == "SELL"){
					double qty = cdbo.getQuantiy() + txn.getQuantity();
					updateClientQty(qty, txn.getClientId());
				}
				
				double creditAmt = cdbo.getCredit() + txn.getTxnCostPaid();
				txnInfo.setCreditAmt(creditAmt);
				
				DeleteTxn(txnInfo);
				
				TxnLog txnLog = new TxnLog();
				txnLog.setClientId(txn.getClientId());
				txnLog.setComsnCost(txn.getTotalComsn());
				txnLog.setComsnType(txn.getComsnType());
				txnLog.setLogStatus("CANCELLED");
				txnLog.setQuantity(txn.getQuantity());
				txnLog.setTraderId(traderId);
				txnLog.setTxnCost(txn.getTxnCost());
				txnLog.setTxnType(txn.getType());
				txnLog.setTxnDate(txn.getTxnDate());
				InsertIntoTxnLog(txnLog);
			}
			
		} catch(Exception e){
			
		}
		
		return b;
	}
	
	public void updateClientQty(double qty, String clientId){
		String query = "update clientdbo set Clientdbo_quantity="+qty+" WHERE clientdbo_id='"+clientId;
		try{
			MySqlExecute.executeUpdateMySqlQuery(query);
		} catch(Exception e){
			log.error("Error in updationg clientdbo");
		}
		
	}
	
	public boolean approveTxn(List<Integer> txnIdList, String traderId){
		boolean b = false;
		for(Integer txnId : txnIdList){
			Txn txn = getClientTxn(txnId);
			TxnInfo txnInfo = new TxnInfo();
			txnInfo.setTxnId(txn.getTxnId());
			txnInfo.setClientId(txn.getClientId());
			ClientDbo cdbo = ViewOilCashReserves(txn.getClientId());
			
			//Provide the updated quantity
			if(txn.getType() == "BUY"){
				double qty = cdbo.getQuantiy() + txn.getQuantity();
				updateClientQty(qty, txn.getClientId());
			}
			
			double creditAmt = cdbo.getCredit() + txn.getTxnCostPaid();
			txnInfo.setCreditAmt(creditAmt);
			
			DeleteTxn(txnInfo);
			
			TxnLog txnLog = new TxnLog();
			txnLog.setClientId(txn.getClientId());
			txnLog.setComsnCost(txn.getTotalComsn());
			txnLog.setComsnType(txn.getComsnType());
			txnLog.setLogStatus("APPROVED");
			txnLog.setQuantity(txn.getQuantity());
			txnLog.setTraderId(traderId);
			txnLog.setTxnCost(txn.getTxnCost());
			txnLog.setTxnType(txn.getType());
			txnLog.setTxnDate(txn.getTxnDate());
			InsertIntoTxnLog(txnLog);
		}

		return b;
		
		
	}
	
	public List<PaymentForTxn> getPendingPaymentApprovals(){
		String query = "Select * from payment_info where Payment_Status='Pending'";
		List<PaymentForTxn> pymntList = new ArrayList<PaymentForTxn>();
		PaymentForTxn pt;
		try{
			ResultSet rs = MySqlExecute.executeMySqlQuery(query);
			while(rs.next()){
				pt = new PaymentForTxn();
				pt.setPaymentId(rs.getInt("Payment_id"));
				pt.setPaymentDate(rs.getTimestamp("Payment_date"));
				pt.setPaymentAmount(rs.getDouble("Payment_amount"));
				pt.setTxnId(rs.getInt("Payment_txn_id"));
				pt.setStatus(rs.getString("Payment_Status"));
				pt.setTraderId(rs.getString("payment_trader_id"));
				pt.setClientId(rs.getString("Payment_Client_Id"));
				pymntList.add(pt);
			}
		} catch(Exception e){
			log.error("Error in getting pending payment txns"+e);
		}
		
		return pymntList;
	}

	// to fetch data using clientId, fname and city
	public List<UserInfo> FetchClientRecords(String fetchBy, String IdNameCity) {
		String query = "";
		List<UserInfo> clientDetails = new ArrayList<UserInfo>();
		if (fetchBy == "ID") {
			query = "select client_Id,client_fName from Client where clietn_Id = '"
					+ IdNameCity + "'";
		} else if (fetchBy == "NAME") {
			query = "select client_Id,client_fName from Client where client_fname = '"
					+ IdNameCity + "'";
		} else {
			query = "select client_Id,client_fName from Client where client_Id = (select Address_Client_Id "
					+ "from address where Address_city = '" + IdNameCity + "')";
		}
		try {
			ResultSet rs = MySqlExecute.executeMySqlQuery(query);
			while (rs.next()) {
				UserInfo client = new UserInfo();
				client.setfName(rs.getString("Client_Id"));
				client.setClientId(rs.getString("Client_fname"));
				clientDetails.add(client);
			}
			return clientDetails;
		} catch (Exception e) {
			log.error("Error while fetching records of client " + e);
		}
		return clientDetails;
	}
	
	public void approvePymnt(List<Integer> pIdList, String traderId){
		for(int pid: pIdList){
			approvePayment(pid, traderId);
		}
	}
	public void approvePayment(int pid, String traderId){
		String query = "update payment_info set Payment_status='Approved', payment_trader_id='"
						+traderId+"' where Payment_id="+pid;
		try{
			MySqlExecute.executeUpdateMySqlQuery(query);
		} catch(Exception e){
			log.error("Erroor in approving Payment"+e);
		}
	}
	
}
