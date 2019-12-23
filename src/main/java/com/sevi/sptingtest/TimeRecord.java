package com.sevi.sptingtest;

import java.util.Date;

public class TimeRecord {
	String productId;
	Date timeOfRecord;

	public TimeRecord(String id, Date date) {
		setProductId(id);
		setTimeOfRecord(date);
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String id) {
		this.productId = id;
	}

	public Date getTimeOfRecord() {
		return timeOfRecord;
	}

	public void setTimeOfRecord(Date timeOfRecord) {
		this.timeOfRecord = timeOfRecord;
	}

}
