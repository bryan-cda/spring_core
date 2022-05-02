package br.com.spring.meetapp;

import br.com.spring.meetapp.config.FileStorageConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({FileStorageConfig.class})
public class MeetApp {
	public static void main(String[] args) {
		SpringApplication.run(MeetApp.class, args);
	}
}
