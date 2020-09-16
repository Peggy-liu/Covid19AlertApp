package com.alert.example.service;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.alert.example.controller.AlertController;
import com.alert.example.model.RegionalAlertData;

/**
 * 
 * Often @WebMvcTest will be limited to a single controller and used in
 * combination with @MockBean to provide mock implementations for required
 * collaborators.
 * 
 * Unit test use mock, integration test do not use mock.
 *
 */
@WebMvcTest
public class AlertControllerUnitTest {

	@Autowired
	private AlertController controller;

	@Autowired
	MockMvc mvc;

	@MockBean
	private DataProcessor processor;

	@Test
	public void getRegionalAlertTest() throws Exception {
		RegionalAlertData data = new RegionalAlertData();
		data.setAlert("GREEN");
		data.setMeasurements(Arrays.asList("everything is normal!"));

		Mockito.when(processor.getRegionalAlertData(ArgumentMatchers.anyString())).thenReturn(data);

		mvc.perform(get("/india/delhi")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				// don't forget the curly bracket{}
				.andExpect(content().json(
						"{\"alert\":\"GREEN\",\"measurements\":[\"everything is normal!\"],\"regionalData\":null}"))
				.andExpect(jsonPath("$.measurements", hasItem("everything is normal!")));
	}
}
