package com.oquintero.coinscombination.api.controller;

import java.util.HashMap;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.oquintero.coinscombination.api.models.Coin;
import com.oquintero.coinscombination.api.services.ICoinsCombinationService;

/**
 * API Controller used to expose method to calculate the coins combination from a cash value.
 * 
 * @author Octavio Quintero
 * @since 1.0
 * @version 1.0
 */

@CrossOrigin(origins = "*")
@RestController()
public class CoinsCombinationController {
	private static final Logger log = LoggerFactory.getLogger(CoinsCombinationController.class);
	
	@Autowired
	ICoinsCombinationService service;
	
	/**
	 * Returns an object with the best combination and distribution of coins for a specific cash value.
	 * This combination will have the minimum coins with the total of value received in parameter.
	 * 
	 * @param cash	Value of cash to find the combination of coins
	 * 
	 * @return	JSON Object		Json object with response status and result object with distribution of coins
	 */

	@GetMapping("/coins-combination/{cash}")
	public ResponseEntity<Object> getCombination(@PathVariable float cash){
		HashMap<String, Object> map = new HashMap<>();
		try {
			List<Coin> coins = service.getCombination(cash);
			//Iterate result of combination to create a map to return a single object
			coins.forEach(e -> { map.put(e.getName(), e.getQuantity());});
			
		}catch(Exception ex) {
			
			log.error(this.getClass().getName(), ex);
			new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Object>(map, HttpStatus.OK);
	}

}
