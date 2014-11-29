package com.ots.beans;

public class CreditCard {
	private String cardNo;
	private String cardName;
	private String expMon;
	private String expYr;
	private String cvv;
	private String clientId;

	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public String getExpMon() {
		return expMon;
	}
	public void setExpMon(String expMon) {
		this.expMon = expMon;
	}
	public String getExpYr() {
		return expYr;
	}
	public void setExpYr(String expYr) {
		this.expYr = expYr;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	
}
