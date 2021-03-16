package com.oquintero.coinscombination.api.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.oquintero.coinscombination.api.core.exceptions.CoinNotFoundException;
import com.oquintero.coinscombination.api.models.Coin;
import com.oquintero.coinscombination.api.repository.ICoinRepository;
import com.oquintero.coinscombination.api.services.ICoinService;

/**
 * Service implementation to manipulate the information of coins.
 * 
 * @author Octavio Quintero
 * @since 1.0
 * @version 1.0
 *
 */
@Service
public class CoinServiceImpl implements ICoinService {
	
	private static final Logger log = LoggerFactory.getLogger(CoinServiceImpl.class);

	@Autowired
	ICoinRepository repository;
	
	/**
	 * Service method to return all coins available in database.
	 * 
	 * @return List<Coin> 		List of coins
	 */
	public List<Coin> getAll() {
		List<Coin> coins = null;
		try {
			coins = repository.findAll();
		}catch(Exception ex) {
			log.error(this.getClass().getName(), ex);
			throw ex;
		}
		return coins;
	}
	
	/**
	 * Service method to find a coin by id number.
	 * 
	 * @param id	Number id of coin.
	 * 
	 * @return Coin 		Object with coin data.
	 */
	public Coin getById(Long id) {
		Coin coin = null;
		try {
			coin = repository.findById(id).orElseThrow(() -> new CoinNotFoundException());
		}catch(CoinNotFoundException ex) {
			log.error(this.getClass().getName(), ex);
			throw ex;
		}
		return coin;
	}


	/**
	 * Service to create a new coin.
	 * 
	 * @param newCoin	Object with all information about coin.
	 * 
	 * @return Coin		New coin added to database.
	 */
	public Coin add(Coin newCoin) {
		Coin coin = null;
		try {
			coin = repository.save(newCoin);
		}catch(Exception ex) {
			log.error(this.getClass().getName(), ex);
			throw ex;
		}
		return coin;
	}

	/**
	 * Service to update a specific coin or add new if not exists in database.
	 * 
	 * @param id	Number id of coin to update
	 * 
	 * @param coinUpdated	Object with information
	 * 
	 * @return Coin		Object with coin updated
	 */
	public Coin update(Long id, Coin coinUpdated) {
	  Coin coin = null;
	  
	  try {
		  coin = repository.findById(id).get();
		  
		  if(coin != null) {
			coin.setName(coinUpdated.getName());
			coin.setValue(coinUpdated.getValue());
			coin = repository.save(coin);
		  }else {
			coinUpdated.setId(id);
			coin = repository.save(coinUpdated);
		  }
		  
	  }catch(Exception ex) {
		  log.error(this.getClass().getName(), ex);
		  throw ex;
	  }
	  
	  return coin;
	}

	
	/**
	 * Method to delete a coin.
	 * 
	 * @param id	Number id of coin to delete.
	 * 
	 * @return	None
	 */
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}catch(Exception ex) {
			log.error(this.getClass().getName(), ex);
			throw ex;
		}
		
	}
	
	
}
