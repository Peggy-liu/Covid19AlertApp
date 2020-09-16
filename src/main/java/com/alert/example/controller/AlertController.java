package com.alert.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alert.example.model.RegionalAlertData;
import com.alert.example.model.SummaryData;
import com.alert.example.service.DataProcessor;

@RestController
@RequestMapping("/india")
public class AlertController {
	
	@Autowired
	private DataProcessor dataProcessor;
	
	@GetMapping("/{state}")
	public RegionalAlertData getRegionalAlert(@PathVariable("state") String state) {
		return dataProcessor.getRegionalAlertData(state);
	}
	
	@GetMapping("/summary")
	public SummaryData getSummary() {
		return dataProcessor.getSummaryData();
	}
	
	
}
