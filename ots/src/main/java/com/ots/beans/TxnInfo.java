package com.ots.beans;

public class TxnInfo {
	
	private int txnId;
	private String clientId;
	private double txnamnt;
	private double creditAmt;
	public double getCreditAmt() {
		return creditAmt;
	}
	public void setCreditAmt(double creditAmt) {
		this.creditAmt = creditAmt;
	}
	private double qty;
	
	public double getQty() {
		return qty;
	}
	public void setQty(double qty) {
		this.qty = qty;
	}
	public double getTxnamnt() {
		return txnamnt;
	}
	public void setTxnamnt(double txnamnt) {
		this.txnamnt = txnamnt;
	}
	public int getTxnId() {
		return txnId;
	}
	public void setTxnId(int txnId) {
		this.txnId = txnId;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
}
