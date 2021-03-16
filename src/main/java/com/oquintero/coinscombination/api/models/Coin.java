package com.oquintero.coinscombination.api.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

/***
 * Model class to represent a coin.
 * 
 * @author Octavio Quintero
 * @since 1.0
 * @version 1.0
 *
 */

@Entity
public class Coin {

	public Coin() {
	}

	public Coin(long id, String name, float value) {
		this.id = id;
		this.name = name;
		this.value = value;
	}

	/**
	 * ID Number of coin
	 */
	@Id
	@GeneratedValue
	private long id;

	/**
	 * Name of coin
	 */

	private String name;

	/**
	 * Value of coin
	 */
	private float value;
	
	/**
	 * Quantity of coin
	 */
	@Transient
	private int quantity;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
	
}
