package br.com.springawsms.controller;

import br.com.springawsms.jwt.JWTTokenProvider;
import br.com.springawsms.repository.UserRepository;
import br.com.springawsms.vo.AccountCredentialsVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JWTTokenProvider tokenProvider;
    private final UserRepository repository;

    public AuthController(AuthenticationManager authenticationManager, JWTTokenProvider tokenProvider, UserRepository repository){
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.repository = repository;
    }

    @PostMapping(value = "/signin", produces = {"application/json"})
    public ResponseEntity<Map<Object, Object>> signin(@RequestBody AccountCredentialsVO credentials){
        try{
            var username = credentials.getUserName();
            var password = credentials.getPassword();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            var user = repository.findByUsername(username);

            var token = "";

            if(Objects.nonNull(user)){
                token = tokenProvider.createToken(username, user.getRoles());
            } else{
                throw new UsernameNotFoundException(String.format("Username %s not found", username));
            }

            Map<Object, Object> generatedToken = new HashMap<>();
            generatedToken.put("Username", username);
            generatedToken.put("token", token);

            return new ResponseEntity<>(generatedToken, HttpStatus.OK);
        }catch (AuthenticationException e){
            throw new BadCredentialsException("Invalid username/password supplied.");
        }
    }

}
