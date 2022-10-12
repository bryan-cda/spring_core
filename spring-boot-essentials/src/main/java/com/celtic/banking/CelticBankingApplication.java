package com.celtic.banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.celtic.banking")
public class CelticBankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(CelticBankingApplication.class, args);
	}

}
