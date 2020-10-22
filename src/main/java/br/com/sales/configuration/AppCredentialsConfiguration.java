package br.com.sales.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppCredentialsConfiguration {

    @Bean(name = "loggedIn")
    public String getUserLoggedInSO () {
        return System.getProperty("user.name");
    }
}

