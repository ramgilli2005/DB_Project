package com.ots.beans;

public class TxnInfo {
	
	private int txnId;
	private String clientId;
	private double txnamnt;
	
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
