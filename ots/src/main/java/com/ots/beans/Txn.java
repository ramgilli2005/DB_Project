package com.ots.beans;

import java.util.Date;

public class Txn {
	
	private int txnId;
	private Date txnDate;
	private double quantity;
	private String type;
	private String comsnType;
	private	String clientId;
	private	String traderId;
	private	double totalComsn;
	private double txnCost;
	private double txnCostPaid;
	
	public double getTxnCostPaid() {
		return txnCostPaid;
	}
	public void setTxnCostPaid(double txnCostPaid) {
		this.txnCostPaid = txnCostPaid;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getComsnType() {
		return comsnType;
	}
	public void setComsnType(String comsnType) {
		this.comsnType = comsnType;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getTraderId() {
		return traderId;
	}
	public void setTraderId(String traderId) {
		this.traderId = traderId;
	}
	public double getTotalComsn() {
		return totalComsn;
	}
	public void setTotalComsn(double totalComsn) {
		this.totalComsn = totalComsn;
	}
	public double getTxnCost() {
		return txnCost;
	}
	public void setTxnCost(double txnCost) {
		this.txnCost = txnCost;
	}
	public int getTxnId() {
		return txnId;
	}
	public void setTxnId(int txnId) {
		this.txnId = txnId;
	}
	public Date getTxnDate() {
		return txnDate;
	}
	public void setTxnDate(Date txnDate) {
		this.txnDate = txnDate;
	}
}
