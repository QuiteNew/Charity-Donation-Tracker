package com.uacs.charity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan; // Import this

@SpringBootApplication
@ComponentScan(basePackages = "com.uacs.charity") // Add this line
public class DonationTrackerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DonationTrackerApplication.class, args);
        System.out.println(">>> Charity Donation Tracker API Successfully Launched!");
    }
}