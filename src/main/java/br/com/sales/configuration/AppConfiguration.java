package br.com.sales.configuration;

import br.com.sales.annotations.Development;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@Development
public class AppConfiguration {

    @Value("${application.name}")
    String appName;

    @Bean
    public CommandLineRunner profileManager(){
        return args -> {
            System.out.println("APP NAME: ".concat(appName).toUpperCase());
            System.out.println("RUNNING IN DEVELOPMENT MODE!");
        };
    }
}
