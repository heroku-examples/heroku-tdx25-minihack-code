package com.heroku.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CalculateFinanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CalculateFinanceApplication.class, args);
    }

}
