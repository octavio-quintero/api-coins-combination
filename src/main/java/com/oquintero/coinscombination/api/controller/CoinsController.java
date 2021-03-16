package com.oquintero.coinscombination.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.oquintero.coinscombination.api.core.exceptions.CoinNotFoundException;
import com.oquintero.coinscombination.api.models.Coin;
import com.oquintero.coinscombination.api.services.ICoinService;

/**
 * API Controller with CRUD Operations for the coins used for cash combination.
 * 
 * @author Octavio Quintero
 * @since 1.0
 * @version 1.0
 */

@CrossOrigin(origins = "*")
@RestController()
public class CoinsController {

	private static final Logger log = LoggerFactory.getLogger(CoinsController.class);
	
	@Autowired
	ICoinService service;
	
	/**
	 * Method to return a list of current coins.
	 * 
	 * @return List<Coin> List of coins.
	 */
	@GetMapping("/coins")
	public ResponseEntity<List<Coin> > getAll(){
		List<Coin> coins = null;
		try {
			coins = service.getAll();
			
		}catch(Exception ex) {
			log.error(this.getClass().getName(), ex);
			return new ResponseEntity<List<Coin> >(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Coin>>(coins, HttpStatus.OK);
	}
	
	/**
	 * Method to create a new coin.
	 * 
	 * @param newCoin	Object with all information about coin.
	 * 
	 * @return Coin		New coin added to database.
	 */
	@PostMapping("/coins")
	public ResponseEntity<Coin> addcoin(@RequestBody Coin newCoin) {
		Coin coin = null;
		try {
			coin = service.add(newCoin);
		}catch(Exception ex) {
			log.error(this.getClass().getName(), ex);
			return new ResponseEntity<Coin>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Coin>(coin,HttpStatus.CREATED);
		
	}
	
	/**
	 * Method used to find a coin by id number.
	 * 
	 * @param id	Number id of coin.
	 * 
	 * @return Coin 		Object with coin data.
	 */
	@GetMapping("/coins/{id}")
	public Coin getCoinById(@PathVariable Long id) {
		Coin coin = null;
		try {
		coin = service.getById(id);
		}catch(CoinNotFoundException ex) {
			log.error(this.getClass().getName(), ex);
			throw ex;
		}
		return coin;
	}
	
	/**
	 * Method used to update a specific coin or add new if not exists in database.
	 * 
	 * @param id	Number id of coin to update
	 * 
	 * @param coinUpdated	Object with information to update in coin
	 * 
	 * @return Coin		Coin updated
	 */
	@PutMapping("/coins/{id}")
	public ResponseEntity<Coin> updateCoin(@PathVariable Long id, @RequestBody Coin coinUpdated) {
		Coin coin = null;
		try {
			coin = service.update(id, coinUpdated);
		}catch(Exception ex) {
			return new ResponseEntity<Coin>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Coin>(coin, HttpStatus.OK);
	}
	
	/**
	 * Method to delete a coin.
	 * 
	 * @param id	Number id of coin to delete.
	 * 
	 * @return	None
	 */
	@DeleteMapping("/coins/{id}")
	public ResponseEntity<?> deleteCoin(@PathVariable Long id) {
		try {
			service.delete(id);
		}catch(Exception ex) {
			log.error(this.getClass().getName(), ex);
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
}
