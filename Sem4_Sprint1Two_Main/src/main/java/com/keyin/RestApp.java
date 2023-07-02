package com.keyin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// run HTTP program first, then run this program.
@SpringBootApplication
public class RestApp {

    public static void main(String[] args) {

        SpringApplication.run(RestApp.class, args);
    }

}