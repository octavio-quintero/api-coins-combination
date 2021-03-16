package com.oquintero.coinscombination.api.core.exceptions;

/**
 * This exception when a coin there was not found in the database. 
 *  
 * @author Octavio Quintero
 * @since 1.0
 * @version 1.0
 *
 */
public class CoinNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CoinNotFoundException(){
		super("The coin it's not available in database!!!");
	}
}
