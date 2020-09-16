package com.alert.example.model;

import java.util.List;

public class RegionalAlertData {

	private String alert;
	private List<String> measurements;
	private RegionalData regionalData;

	public String getAlert() {
		return alert;
	}

	public void setAlert(String alert) {
		this.alert = alert;
	}

	public List<String> getMeasurements() {
		return measurements;
	}

	public void setMeasurements(List<String> measurements) {
		this.measurements = measurements;
	}

	public RegionalData getRegionalData() {
		return regionalData;
	}

	public void setRegionalData(RegionalData regionalData) {
		this.regionalData = regionalData;
	}

}
