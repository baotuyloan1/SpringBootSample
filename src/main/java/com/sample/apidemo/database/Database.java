package com.sample.apidemo.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sample.apidemo.models.Product;
import com.sample.apidemo.repositories.ProductRepository;

@Configuration
public class Database {
//	logger
	private static final Logger logger = LoggerFactory.getLogger(Database.class);

	@Bean
	CommandLineRunner initDatabase(ProductRepository productRepository) {

		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				Product productA = new Product("Macbook Pro 16", 2020, 2400.0, "");
				Product productB = new Product("iPad Air Green", 2021, 599.0, "");
				logger.info("Insert data: " + productRepository.save(productA));
				logger.info("Insert data: " + productRepository.save(productB));
				logger.info(productRepository.findAll().toString());
			}
		};
	}
}
