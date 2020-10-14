package br.com.sales.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    @Bean(name = "app-name")
    public String appName(){
        return "Sales in Rio";
    }
}
