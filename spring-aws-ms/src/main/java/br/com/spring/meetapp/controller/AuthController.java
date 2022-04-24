package br.com.spring.meetapp.controller;

import br.com.spring.meetapp.repository.UserRepository;
import br.com.spring.meetapp.jwt.JWTTokenProvider;
import br.com.spring.meetapp.vo.AccountCredentialsVO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;

    private final JWTTokenProvider tokenProvider;

    private final UserRepository userRepository;

    public AuthController(AuthenticationManager authenticationManager, JWTTokenProvider jwtTokenProvider, UserRepository userRepository){
        this.authenticationManager = authenticationManager;
        this.tokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
    }

    @SuppressWarnings("rawtypes")
    @PostMapping(value = "/sig-in", produces = { "application/json", "application/xml", "application/x-yaml" },
            consumes = { "application/json", "application/xml", "application/x-yaml" })
    public ResponseEntity signIn(@RequestBody AccountCredentialsVO data) {
        try {
            var username = data.getUsername();
            var password = data.getPassword();

            var user = userRepository.findByUsername(username);

            var token = "";

            if (user != null) {
                token = tokenProvider.createToken(username, user.getRoles());
            } else {
                throw new UsernameNotFoundException("Username " + username + " not found!");
            }

            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("token", token);
            return ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied!");
        }
    }
}