package com.oquintero.coinscombination.api.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.oquintero.coinscombination.api.models.Coin;
import com.oquintero.coinscombination.api.repository.ICoinRepository;

/**
 * Class used to initialize the default values in database regarding to initial requirement.
 * 
 * @author Octavio Quintero
 * @since 1.0
 * @version 1.0
 *
 */
@Configuration
public class InitializeDatabase {
	
	private static final Logger log = LoggerFactory.getLogger(InitializeDatabase.class);
	
	/**
	 * Method to return CommandLineRunner to initialize the database during starting server.
	 * 
	 * @param coinRepository		Repository class to save records
	 * 
	 * @return CommandLineRunner
	 */
	@Bean
	public CommandLineRunner load(ICoinRepository coinRepository) {
		return args -> {
			log.info("Initializing database");
			log.info("Adding initial records");
			
			try {
				coinRepository.save(new Coin(1, "Penny", .01f));
				coinRepository.save(new Coin(2, "Nickel", 0.05f));
				coinRepository.save(new Coin(3, "Dime", .10f));
				coinRepository.save(new Coin(4, "Quarter", .25f));
				coinRepository.save(new Coin(5, "Half Dollar", .5f));
				coinRepository.save(new Coin(6, "Silver Dollar", 1));
			}catch(Exception ex) {
				log.error("Error during initialization of database. ", ex);
			}
			
			log.info("Database initialized successfully");

		};
	}

}
