package com.oquintero.coinscombination.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oquintero.coinscombination.api.models.Coin;

/**
 * Service used to calculate the combination of coins from a specific value.
 *  
 * @author Octavio Quintero
 *
 */
@Service
public interface ICoinsCombinationService {
	
	List<Coin> getCombination(float cash);

}
