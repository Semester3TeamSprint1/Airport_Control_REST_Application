package com.keyin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// ** to run the api, run this application **
// bootstrapping class for spring boot

@SpringBootApplication
public class RestApp {

    public static void main(String[] args) {

        SpringApplication.run(RestApp.class, args);
    }

}