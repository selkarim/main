package com.exalt.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.exalt")
public class BankApplication {

    public static void main(final String[] args) {
        SpringApplication.run(BankApplication.class, args);

    }
}
