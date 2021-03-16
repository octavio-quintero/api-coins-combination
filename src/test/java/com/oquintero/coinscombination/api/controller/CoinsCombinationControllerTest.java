package com.oquintero.coinscombination.api.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.oquintero.coinscombination.api.services.ICoinService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import com.oquintero.coinscombination.api.services.ICoinService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class CoinsCombinationControllerTest {

	@Autowired
	ICoinService coinService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getCoinsCombinationFor_99Cents() throws Exception {
		mockMvc.perform(get("/coins-combination/.99").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.[\"Silver Dollar\"]", equalTo(0)))
				.andExpect(jsonPath("$.[\"Half Dollar\"]", equalTo(1)))
				.andExpect(jsonPath("$.[\"Quarter\"]", equalTo(1)))
				.andExpect(jsonPath("$.[\"Dime\"]", equalTo(2)))
				.andExpect(jsonPath("$.[\"Nickel\"]", equalTo(0)))
				.andExpect(jsonPath("$.[\"Penny\"]", equalTo(4)));

	}
	
	@Test
	public void getCoinsCombinationFor_1Dollar_and_56Cents() throws Exception {
		mockMvc.perform(get("/coins-combination/1.56").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.[\"Silver Dollar\"]", equalTo(1)))
				.andExpect(jsonPath("$.[\"Half Dollar\"]", equalTo(1)))
				.andExpect(jsonPath("$.[\"Quarter\"]", equalTo(0)))
				.andExpect(jsonPath("$.[\"Dime\"]", equalTo(0)))
				.andExpect(jsonPath("$.[\"Nickel\"]", equalTo(1)))
				.andExpect(jsonPath("$.[\"Penny\"]", equalTo(1)));

	}
	
	@Test
	public void getCoinsCombinationFor_12Dollar_and_85Cents() throws Exception {
		mockMvc.perform(get("/coins-combination/12.85").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.[\"Silver Dollar\"]", equalTo(12)))
				.andExpect(jsonPath("$.[\"Half Dollar\"]", equalTo(1)))
				.andExpect(jsonPath("$.[\"Quarter\"]", equalTo(1)))
				.andExpect(jsonPath("$.[\"Dime\"]", equalTo(1)))
				.andExpect(jsonPath("$.[\"Nickel\"]", equalTo(0)))
				.andExpect(jsonPath("$.[\"Penny\"]", equalTo(0)));

	}
}
