package com.oquintero.coinscombination.api.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.oquintero.coinscombination.api.models.Coin;

/**
 * Service interface to define the operations for coins.
 * 
 * @author Octavio Quintero
 * @since 1.0
 * @version 1.0
 *
 */
@Service
public interface ICoinService {

	/**
	 * Service method used to return all coins configured in database.
	 */
	List<Coin> getAll();
	
	/**
	 * Service method used to find a coin by id number.
	 * 
	 * @param id	Number id of coin.
	 * 
	 * @return Coin 		Object with data of coin.
	 */
	Coin getById(Long id);
	
	/**
	 * Service used to create a new Coin.
	 * 
	 * @param newCoin	Object with all information of coin.
	 * 
	 * @return Coin		New coin added to database.
	 */
	Coin add(Coin newCoin);
	
	/**
	 * Service used to update a specific coin or add new if not exists in database.
	 * 
	 * @param id	Number id of coin to update
	 * 
	 * @param coinUpdated	Object with information to update in coin
	 * 
	 * @return Coin		Object with coin updated
	 */
	Coin update(Long id, Coin coinUpdated);
	
	/**
	 * Method used to delete a coin.
	 * 
	 * @param id	Number id of coin to delete.
	 * 
	 * @return	None
	 */
	void delete(Long id);
}
