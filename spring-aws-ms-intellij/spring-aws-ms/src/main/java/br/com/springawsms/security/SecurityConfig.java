package br.com.springawsms.security;

import br.com.springawsms.jwt.JWTConfigure;
import br.com.springawsms.jwt.JWTTokenProvider;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JWTTokenProvider tokenProvider;

    public SecurityConfig(JWTTokenProvider tokenProvider){
        this.tokenProvider = tokenProvider;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @SneakyThrows
    @Bean
    @Override
    public AuthenticationManager authenticationManager(){
        return super.authenticationManagerBean();
    }

    @SneakyThrows
    protected void configure(HttpSecurity http){
        http.httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/auth/signin", "/api-docs", "swagger-ui.html**").permitAll()
                .antMatchers("/api").authenticated()
                .antMatchers("/users").denyAll()
                .and()
                .apply(new JWTConfigure(tokenProvider));
    }

}
