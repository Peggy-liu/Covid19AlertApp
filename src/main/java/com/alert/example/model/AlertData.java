package com.alert.example.model;

import java.util.List;

public class AlertData {

	private SummaryData summary;
	private List<RegionalData> regional;

	public SummaryData getSummary() {
		return summary;
	}

	public void setSummary(SummaryData summary) {
		this.summary = summary;
	}

	public List<RegionalData> getRegional() {
		return regional;
	}

	public void setRegional(List<RegionalData> regional) {
		this.regional = regional;
	}

}
