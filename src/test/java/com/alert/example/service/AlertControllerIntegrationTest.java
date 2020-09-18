package com.alert.example.service;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
 class AlertControllerIntegrationTest {

	@Autowired
	MockMvc mvc;

	
	/**
	 * get("/india/delhi")will actually call the remote API service.
	 * Integration test has no mocking data. 
	 * @throws Exception
	 */
	@Test
	 void getRegionalAlertTest() throws Exception {

		mvc.perform(get("/india/delhi")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				// don't forget the curly bracket{}
				.andExpect(content().json(
						"{\"alert\":\"RED\",\"measurements\":[\"Total lockdown!\"],\"regionalData\":{\"loc\":\"Delhi\",\"confirmedCasesIndian\":225795,\"confirmedCasesForeign\":1,\"discharged\":191203,\"deaths\":4806,\"totalConfirmed\":225796}}"))
				.andExpect(jsonPath("$.measurements", hasItem("Total lockdown!")));
	}
}
