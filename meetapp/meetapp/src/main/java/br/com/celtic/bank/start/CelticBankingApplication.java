package br.com.celtic.bank.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import static java.util.List.of;

@SpringBootApplication
@ComponentScan(basePackages = {"br.com.celtic.bank"})
public class CelticBankingApplication {
	public static void main(String[] args) {
		SpringApplication.run(CelticBankingApplication.class, args);
	}
}
