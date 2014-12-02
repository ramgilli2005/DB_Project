package com.ots.beans;

import java.sql.Timestamp;

public class AggregateInfo {
	private double aggregateValue;
	private Timestamp startDate;
	private Timestamp endDate;
	private long count;

	public double getAggregateValue() {
		return aggregateValue;
	}

	public void setAggregateValue(double aggregateValue) {
		this.aggregateValue = aggregateValue;
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}
}