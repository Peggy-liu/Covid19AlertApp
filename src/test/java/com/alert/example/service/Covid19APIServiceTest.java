package com.alert.example.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.alert.example.exception.StateNotFoundException;
import com.alert.example.model.AlertData;
import com.alert.example.model.Covid19DataModel;
import com.alert.example.model.RegionalData;
import com.alert.example.model.SummaryData;



 class Covid19APIServiceTest {

	@InjectMocks
	private Covid19APIService service;

	@Mock
	private RestTemplate restTemplate;

	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);

	}

	@Test
	@DisplayName("when state name is found")
	 void getRegionalDataTest1() {
		Covid19DataModel apidata = new Covid19DataModel();
		AlertData alert = new AlertData();
		RegionalData region = new RegionalData();
		region.setLoc("Place");
		alert.setRegional(Arrays.asList(region));
		apidata.setData(alert);

		Mockito.when(restTemplate.getForObject(ArgumentMatchers.anyString(), ArgumentMatchers.any()))
				.thenReturn(apidata);

		RegionalData actual = service.getRegionalData("Place");
		assertEquals(region.getLoc(), actual.getLoc());

	}

	@Test
	@DisplayName("when state name is not found, throw exception")
	 void getRegionalDataTest2() {
		Covid19DataModel apidata = new Covid19DataModel();
		AlertData alert = new AlertData();
		RegionalData region = new RegionalData();
		region.setLoc("Place");
		alert.setRegional(Arrays.asList(region));
		apidata.setData(alert);

		Mockito.when(restTemplate.getForObject(ArgumentMatchers.anyString(), ArgumentMatchers.any()))
				.thenReturn(apidata);

		assertThrows(StateNotFoundException.class, () -> service.getRegionalData("something"));

	}

	@Test
	 void getSummaryTest() {
		Covid19DataModel apidata = new Covid19DataModel();
		AlertData alert = new AlertData();

		SummaryData summary = new SummaryData();
		summary.setTotal(2000);
		summary.setConfirmedButLocationUnidentified(3);
		summary.setConfirmedCasesForeign(4);
		summary.setConfirmedCasesIndian(4);
		summary.setDeaths(3);
		summary.setDischarged(10);
		alert.setSummary(summary);
		apidata.setData(alert);

		Mockito.when(restTemplate.getForObject(ArgumentMatchers.anyString(), ArgumentMatchers.any()))
				.thenReturn(apidata);

		SummaryData actual = service.getSummary();
		assertAll(() -> assertEquals(2000, actual.getTotal()),
				() -> assertEquals(3, actual.getConfirmedButLocationUnidentified()),
				() -> assertEquals(4, actual.getConfirmedCasesForeign()),
				() -> assertEquals(4, actual.getConfirmedCasesIndian()), () -> assertEquals(3, actual.getDeaths()),
				() -> assertEquals(10, actual.getDischarged()));

	}
}
