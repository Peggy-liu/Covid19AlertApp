package com.alert.example.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.alert.example.model.RegionalAlertData;
import com.alert.example.model.RegionalData;
import com.alert.example.model.SummaryData;

public class DataProcessorTests {

	
	@InjectMocks
	private DataProcessor dataProcessor;
	
	@Mock
	private Covid19APIService service;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	@Test
	@DisplayName("When the total cases are less than 1000")
	public void getRegionalAlertDataTest1() {
		RegionalData data = new RegionalData();
		data.setTotalConfirmed(100);
		Mockito.when(service.getRegionalData(ArgumentMatchers.anyString())).thenReturn(data);
		
		RegionalAlertData actualData = dataProcessor.getRegionalAlertData("some string");
	
		assertEquals("GREEN", actualData.getAlert());
		assertEquals(Arrays.asList("Everything is normal!"), actualData.getMeasurements());
		assertEquals(data, actualData.getRegionalData());
		Mockito.verify(service).getRegionalData(ArgumentMatchers.anyString());
		
	}
	
	
	@Test
	@DisplayName("When the total cases are >1000 and <10000")
	public void getRegionalAlertDataTest2() {
		RegionalData data = new RegionalData();
		data.setTotalConfirmed(5000);
		Mockito.when(service.getRegionalData(ArgumentMatchers.anyString())).thenReturn(data);
		
		RegionalAlertData actualData = dataProcessor.getRegionalAlertData("some string");
	
		assertEquals("ORANGE", actualData.getAlert());
		assertEquals(Arrays.asList("Only a list of essential service are available.....", "a list of service......"), actualData.getMeasurements());
		assertEquals(data, actualData.getRegionalData());
		Mockito.verify(service).getRegionalData(ArgumentMatchers.anyString());
		
	}
	
	
	@Test
	@DisplayName("When the total cases are more than 10000")
	public void getRegionalAlertDataTest3() {
		RegionalData data = new RegionalData();
		data.setTotalConfirmed(20000);
		Mockito.when(service.getRegionalData(ArgumentMatchers.anyString())).thenReturn(data);
		
		RegionalAlertData actualData = dataProcessor.getRegionalAlertData("some string");
	
		assertEquals("RED", actualData.getAlert());
		assertEquals(Arrays.asList("Total lockdown!"), actualData.getMeasurements());
		assertEquals(data, actualData.getRegionalData());
		Mockito.verify(service).getRegionalData(ArgumentMatchers.anyString());
		
	}
	@Test
	@DisplayName("get summary data test")
	public void getSummaryDataTest() {
		SummaryData data = new SummaryData();
		data.setConfirmedButLocationUnidentified(2);
		data.setConfirmedCasesForeign(5);
		data.setConfirmedCasesIndian(100);
		data.setDeaths(2);
		data.setDischarged(10);
		data.setTotal(1000);
		
		Mockito.when(service.getSummary()).thenReturn(data);
		
		SummaryData actual = dataProcessor.getSummaryData();
		
		assertEquals(data, actual);
		Mockito.verify(service).getSummary();
	}
	
	
}
