package com.anjali.train;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableJpaRepositories("com.anjali.train")
public class TrainManagementApplication {
	@Autowired
    private DataBuffer dataBuffer;
	public static void main(String[] args) {
		SpringApplication.run(TrainManagementApplication.class, args);
	}
	@Bean
    public DataBuffer dataBuffer() {
        return new DataBuffer();
    }
    @Bean
    CommandLineRunner runner(DataBuffer dataBuffer){
        return args -> {
            dataBuffer.createStationMap();
        };
    }

}
