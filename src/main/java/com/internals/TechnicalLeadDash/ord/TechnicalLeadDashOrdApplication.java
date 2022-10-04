package com.internals.TechnicalLeadDash.ord;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories //Activates reactive support
public class TechnicalLeadDashOrdApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechnicalLeadDashOrdApplication.class, args);
	}

}
