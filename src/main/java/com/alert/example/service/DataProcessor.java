package com.alert.example.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alert.example.exception.StateNotFoundException;
import com.alert.example.model.Covid19DataModel;
import com.alert.example.model.RegionalAlertData;
import com.alert.example.model.RegionalData;
import com.alert.example.model.SummaryData;

@Service
public class DataProcessor {

	@Autowired
	private Covid19APIService service;

	public RegionalAlertData getRegionalAlertData(String state) {

		RegionalData regional = service.getRegionalData(state);

		RegionalAlertData result = new RegionalAlertData();
		result.setRegionalData(regional);

		int totalConfirmed = regional.getTotalConfirmed();

		if (totalConfirmed < 1000) {
			result.setAlert("GREEN");
			result.setMeasurements(Arrays.asList("Everything is normal!"));
		} else if (totalConfirmed > 1000 && totalConfirmed < 10000) {
			result.setAlert("ORANGE");
			result.setMeasurements(
					Arrays.asList("Only a list of essential service are available.....", "a list of service......"));
		} else {
			result.setAlert("RED");
			result.setMeasurements(Arrays.asList("Total lockdown!"));
		}

		return result;
	}

	public SummaryData getSummaryData() {

		SummaryData result = service.getSummary();
		
		return result;
	}
}
