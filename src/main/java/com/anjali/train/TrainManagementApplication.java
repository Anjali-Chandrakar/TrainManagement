package com.anjali.train;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.anjali.train")
public class TrainManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainManagementApplication.class, args);
	}

}
