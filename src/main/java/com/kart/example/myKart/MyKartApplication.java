package com.kart.example.myKart;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MyKartApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyKartApplication.class, args);
	}

    @Bean
    public CommandLineRunner commandLineRunner(String[] args) {
        return runner -> {
            System.out.println("Command runner");
        };
    };

}
