package br.com.springawsms.jwt;

import lombok.SneakyThrows;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.FilterChain;
import javax.servlet.GenericFilter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class JWTTokenFilter extends GenericFilter {
    private final JWTTokenProvider jwtTokenProvider;

    public JWTTokenFilter(JWTTokenProvider jwtTokenProvider){
        this.jwtTokenProvider = jwtTokenProvider;
    }
    @SneakyThrows
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);

        if(Objects.nonNull(token) && jwtTokenProvider.validateToken(token)){
            Authentication auth = jwtTokenProvider.getAuthentication(token);
            if(Objects.nonNull(auth)){
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        chain.doFilter(request, response);
    }
}
