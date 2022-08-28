package br.com.spring.pendency.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import static java.util.List.of;

@SpringBootApplication
@ComponentScan(basePackages = "br.com.spring.pendency")
public class PendencyApp {
	public static void main(String[] args) {
		SpringApplication.run(PendencyApp.class, args);
	}
}
