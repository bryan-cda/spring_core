package br.com.spring.meetapp.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import static java.util.List.of;

@SpringBootApplication
@ComponentScan(basePackages = "br.com.spring.meetapp")
public class MeetApp {
	public static void main(String[] args) {
		SpringApplication.run(MeetApp.class, args);
	}
}
