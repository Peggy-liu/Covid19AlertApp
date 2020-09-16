package com.alert.example.model;

import java.time.ZonedDateTime;

public class Covid19DataModel {

	private boolean success;
	private AlertData data;
	private ZonedDateTime lastRefreshed;
	private ZonedDateTime lastOriginUpdate;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public AlertData getData() {
		return data;
	}

	public void setData(AlertData data) {
		this.data = data;
	}

	public ZonedDateTime getLastRefreshed() {
		return lastRefreshed;
	}

	public void setLastRefreshed(ZonedDateTime lastRefreshed) {
		this.lastRefreshed = lastRefreshed;
	}

	public ZonedDateTime getLastOriginUpdate() {
		return lastOriginUpdate;
	}

	public void setLastOriginUpdate(ZonedDateTime lastOriginUpdate) {
		this.lastOriginUpdate = lastOriginUpdate;
	}

}
