package com.anjali.train;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableJpaRepositories("com.anjali.train")
public class TrainManagementApplication {
	@Autowired
    private DataLoader dataBuffer;
	public static void main(String[] args) {
		SpringApplication.run(TrainManagementApplication.class, args);
	}
	@Bean
    public DataLoader dataBuffer() {
        return new DataLoader();
    }
}
