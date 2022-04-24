package br.com.springawsms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringAwsMsApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringAwsMsApplication.class, args);
	}
}
