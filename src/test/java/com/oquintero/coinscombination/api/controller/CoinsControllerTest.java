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

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class CoinsControllerTest {

	@Autowired
    ICoinService coinService;
	
	@Autowired
    private MockMvc mockMvc;
	
	/**
	 * Test for API function to get all coins.
	 * 
	 * @throws Exception
	 */
    @Test
    public void findAllCoins() throws Exception{
    	mockMvc.perform(get("/coins")
    		      .contentType(MediaType.APPLICATION_JSON))
    		      .andExpect(status().isOk())
    		      .andExpect(content()
    		      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
    		      .andExpect(jsonPath("$").isArray());
    }
    
    /**
	 * Test for API function to add new coin.
	 * 
	 * @throws Exception
	 */
    @Test
    public void addCoin() throws Exception{
    	mockMvc.perform(post("/coins")
    			  .content("{"
				          + "  \"name\": \"New Test Coin\", "
				          + "  \"value\": \".3\" "
				          + "}")
    		      .contentType(MediaType.APPLICATION_JSON))
    		      .andExpect(status().isCreated())
    		      .andExpect(content()
    		      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
    		      .andExpect(jsonPath("$.name", equalTo("New Test Coin")));
    }
    
    /**
	 * Test for API function to find coin by id.
	 * 
	 * @throws Exception
	 */
    @Test
    public void findCoinById() throws Exception{ 	
    	mockMvc.perform(get("/coins/5")
    		      .contentType(MediaType.APPLICATION_JSON))
    		      .andExpect(status().isOk())
    		      .andExpect(content()
    		      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
    		      .andExpect(jsonPath("$.id",equalTo(5)));
    }
    
    /**
	 * Test for API function to delete coin.
	 * 
	 * @throws Exception
	 */
    @Test
    public void deleteCoinById() throws Exception{
    	mockMvc.perform(get("/coins/3")
    		      .contentType(MediaType.APPLICATION_JSON))
    		      .andExpect(status().isOk());
    }
}
