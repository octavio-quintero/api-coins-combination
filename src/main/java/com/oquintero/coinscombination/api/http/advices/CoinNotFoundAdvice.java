package com.oquintero.coinscombination.api.http.advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.oquintero.coinscombination.api.core.exceptions.CoinNotFoundException;

/**
 * Exception used to identify when there is not a coin definition in database and 
 * when a CoinNotFoundException was handled, this exception is used to return
 * the HTTP CODE properly as not found.
 * 
 * @author Octavio Quintero
 * @since 1.0
 * @version 1.0
 *
 */
@ControllerAdvice
public class CoinNotFoundAdvice {

	@ResponseBody
	  @ExceptionHandler(CoinNotFoundException.class)
	  @ResponseStatus(HttpStatus.NOT_FOUND)
	  public String coinNotFoundHandler(CoinNotFoundException ex) {
	    return ex.getMessage();
	  }
}
