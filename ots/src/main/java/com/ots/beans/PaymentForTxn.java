package com.ots.beans;

import java.sql.Timestamp;

public class PaymentForTxn {
	
	private int paymentId;
	private double paymentAmount;
	private Timestamp paymentDate;
	private int txnId;
	private String status;
	private String clientId;
	private String traderId;
	private double remainingBalance;
	
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	public double getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	public Timestamp getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Timestamp paymentDate) {
		this.paymentDate = paymentDate;
	}
	public int getTxnId() {
		return txnId;
	}
	public void setTxnId(int txn_id) {
		this.txnId = txn_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public double getRemainingBalance() {
		return remainingBalance;
	}
	public void setRemainingBalance(double remainingBalance) {
		this.remainingBalance = remainingBalance;
	}
	public String getTraderId() {
		return traderId;
	}
	public void setTraderId(String traderId) {
		this.traderId = traderId;
	}
}
