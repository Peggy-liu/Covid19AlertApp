package com.alert.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alert.example.exception.StateNotFoundException;
import com.alert.example.model.Covid19DataModel;
import com.alert.example.model.RegionalData;
import com.alert.example.model.SummaryData;

@Service
public class Covid19APIService {

	@Autowired
	private RestTemplate restTemplate;
	
	private String url = "https://api.rootnet.in/covid19-in/stats/latest";

	public RegionalData getRegionalData(String state) {
		
		Covid19DataModel data = restTemplate.getForObject(url, Covid19DataModel.class);
		return data.getData().getRegional().stream().filter(c -> c.getLoc().equalsIgnoreCase(state)).findFirst()
				.orElseThrow(() -> new StateNotFoundException("State info not found"));
	}
	
	public SummaryData getSummary() {
		Covid19DataModel data = restTemplate.getForObject(url, Covid19DataModel.class);
		return data.getData().getSummary();
	}
}
