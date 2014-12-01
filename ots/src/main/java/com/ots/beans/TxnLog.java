package com.ots.beans;

import java.sql.Timestamp;

public class TxnLog {
	
	private int txnLogId;
	private	String clientId;
	private Timestamp txnDate;
	private double quantity;
	private String txnType;
	private String comsnType;
	private String logStatus;
	private	String traderId;
	private double txnCost;
	private double comsnCost;
	
	public int getTxnLogId() {
		return txnLogId;
	}
	public void setTxnLogId(int txnLogId) {
		this.txnLogId = txnLogId;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public Timestamp getTxnDate() {
		return txnDate;
	}
	public void setTxnDate(Timestamp txnDate) {
		this.txnDate = txnDate;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public String getTxnType() {
		return txnType;
	}
	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}
	public String getComsnType() {
		return comsnType;
	}
	public void setComsnType(String comsnType) {
		this.comsnType = comsnType;
	}
	public String getLogStatus() {
		return logStatus;
	}
	public void setLogStatus(String logStatus) {
		this.logStatus = logStatus;
	}
	public String getTraderId() {
		return traderId;
	}
	public void setTraderId(String traderId) {
		this.traderId = traderId;
	}
	public double getTxnCost() {
		return txnCost;
	}
	public void setTxnCost(double txnCost) {
		this.txnCost = txnCost;
	}
	public double getComsnCost() {
		return comsnCost;
	}
	public void setComsnCost(double comsnCost) {
		this.comsnCost = comsnCost;
	}
}
