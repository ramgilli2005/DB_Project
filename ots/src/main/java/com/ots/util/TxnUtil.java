package com.ots.util;

import org.springframework.stereotype.Component;

@Component
public class TxnUtil {
	private static final String LEVEL_SILVER = "SILVER";
	private static final String LEVEL_GOLD = "GOLD";
	public double computeCashCommission(double oilPrice, double qty, String lvl ){
		double commission = 0.0;
		// 5% Commission
		if(lvl.equals(LEVEL_SILVER)){
			commission = oilPrice * 0.05 * qty;
		} else if(lvl.equals(LEVEL_GOLD)){ //2% commission
			commission = oilPrice * 0.02 * qty;
		}
		
		return commission;
	}
	
	public double computeOilCommission(double oilPrice, double qty, String lvl ){
		
		double oilCommsn = 0.0;
		// 5% Commission
		double commission = computeCashCommission(oilPrice, qty, lvl);
		oilCommsn = commission / oilPrice;
		
		return oilCommsn;
	}
	
	public double computeCashTxnCost(double commsn, double oilPrice, double qty){
		
		double totalCost = commsn + (oilPrice * qty);
		
		return totalCost;
	}
	
	public double computeOilTxnCost(double oilPrice, double qty){
		
		double totalCost = (oilPrice * qty);
		
		return totalCost;
	} 
	
}
