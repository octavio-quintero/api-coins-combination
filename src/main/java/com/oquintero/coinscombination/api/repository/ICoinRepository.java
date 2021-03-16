package com.oquintero.coinscombination.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oquintero.coinscombination.api.models.Coin;

/**
 * Repository interface for coins, this repository will provide all methods
 * to make different operations in database because it's extending 
 * from {@code JpaRepository JpaRepository.class} 
 * and reuse the methods.
 * 
 * @author Octavio Quintero
 * @since 1.0
 * @version 1.0
 *
 */
@Repository
public interface ICoinRepository extends JpaRepository<Coin, Long> {

}
