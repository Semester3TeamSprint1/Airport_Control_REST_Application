// The data controllers for our database. Having an issue where data shows out of order. Also, when POSTing the scripts
// in Postman, an error pops up. Seems like it's trying to continue a loop. Data still posts to Postman. Just unsure how
// to stop that error.
// Darla Ward, Danielle Reid and Jarod Chambers-Genge
// Completed on July 5, 2023

package com.keyin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestApp {

    public static void main(String[] args) {

        SpringApplication.run(RestApp.class, args);
    }

}