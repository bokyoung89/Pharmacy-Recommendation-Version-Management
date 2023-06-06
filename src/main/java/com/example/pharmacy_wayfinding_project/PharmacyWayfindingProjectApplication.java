package com.example.pharmacy_wayfinding_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PharmacyWayfindingProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(PharmacyWayfindingProjectApplication.class, args);
    }

}
