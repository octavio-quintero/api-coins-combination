package com.oquintero.coinscombination.api.services.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oquintero.coinscombination.api.controller.CoinsController;
import com.oquintero.coinscombination.api.models.Coin;
import com.oquintero.coinscombination.api.services.ICoinsCombinationService;

/**
 * Service used to calculate the combination of coins from a specific value.
 *  
 * @author Octavio Quintero
 *
 */
@Service
public class CoinsCombinationServiceImpl implements ICoinsCombinationService {
	
	private static final Logger log = LoggerFactory.getLogger(CoinsController.class);
	
	@Autowired
	CoinServiceImpl service;

	/**
	 * Calculate the best combination of coins from a cash value and
	 * return a list of coins.
	 * 
	 * @param cashValue		Cash value
	 * 
	 * @return List<Coin>	List of coins
	 */
	@Override
	public List<Coin> getCombination(float cashValue) {
		BigDecimal cash = new BigDecimal(cashValue);
		//To force any value to have 2 decimals
		cash = cash.setScale(2, RoundingMode.HALF_UP);
		List<Coin> coins = null;
		try {
			//Get ordered list of coins available in database 
			//to start from coin with high value
			//to reduce the number of coins in distribution
			coins = getCoinsOrdered();
			
			for (Coin coin : coins) {
				//validate if coin value is less than cash to 
				//calculate the quantity of coins, if the coin value is greater
				//iterate for next coin.
				if(coin.getValue() <= cash.floatValue()) {
					cash =  calculate(cash, coin);
				}
			}
			
		}catch(Exception ex) {
			log.error(this.getClass().getName(), ex);
			throw ex;
		}
		
		return coins;
	}	
	
	/**
	 * Return a list of coins ordered in descendant
	 *  
	 * @return List<Coin> 	List of coins
	 */
	private List<Coin> getCoinsOrdered(){
		List<Coin> coins = null;
		try{
			coins = service.getAll();
			if(coins != null && coins.size() > 1) {
				//sort list using comparator with the coin value and 
				//used reversed to have in order descendant
				coins.sort(Comparator.comparing(Coin::getValue).reversed());
			}
		}catch(Exception ex) {
			log.error(this.getClass().getName(), ex);
			throw ex;
		}
		
		return coins;
	}
	
	/**
	 * Calculate the quantity of coins we can give to return of cash for a specific coin.
	 * Used BigDecimals to have more control with the number of decimals in calculation.
	 * We can use the method as recursive to calculate the number of coins we can give, this process
	 * will be iterating to subtract the coin value until the cash is greater than coin value, once
	 * completed for a specific coin value will return the cash remaining to be calculate for
	 * another coin.
	 * 
	 * @param cash		Cash value remaining in calculation.
	 * 
	 * @param coin		Coin with value and quantity in attributes
	 * 
	 * @return	BigDecimal		Remaining cash after calculation
	 */
	private BigDecimal calculate(BigDecimal cash, Coin coin) {
		try {
			//validate if coin value is less than cash
			if(coin.getValue() <= cash.floatValue()) {
				//subtract the coin value to cash for next iteration and reduce the cash 
				//to have an optimal balance with coins 
				cash = cash.subtract(new BigDecimal(coin.getValue()));
				//rounding because could be there loss of decimals in calculation 
				cash = cash.setScale(2, RoundingMode.HALF_UP);
				//increment count for coin
				coin.setQuantity(coin.getQuantity()+1);
				//using recursive method to calculate and for substract the value of cash for same coin
				return calculate(cash, coin);
			}else {
				//remaining cash to calculate for next coin
				return cash;
			}
		}catch(Exception ex) {
			log.error(this.getClass().getName(), ex);
			throw ex;
		}
		
	}
	

}
