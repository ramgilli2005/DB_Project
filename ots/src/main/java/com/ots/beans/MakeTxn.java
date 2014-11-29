package com.ots.beans;

public class MakeTxn {
	
	private double quantity;
	private String type;
	private String comsnType;
	private	double comnsPaid;
	private	String clientId;
	private	String traderId;
	private	double totalComns;
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
	public double getComnsPaid() {
		return comnsPaid;
	}
	public void setComnsPaid(double comnsPaid) {
		this.comnsPaid = comnsPaid;
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
	public double getTotalComns() {
		return totalComns;
	}
	public void setTotalComns(double totalComns) {
		this.totalComns = totalComns;
	}
	public double getTxnCost() {
		return txnCost;
	}
	public void setTxnCost(double txnCost) {
		this.txnCost = txnCost;
	}

}
