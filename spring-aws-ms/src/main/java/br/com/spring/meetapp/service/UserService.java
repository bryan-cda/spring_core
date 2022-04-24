package br.com.spring.meetapp.service;

import br.com.spring.meetapp.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository repository;

    public UserService(UserRepository repository){
        this.repository = repository;
    }
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        var username = repository.findByUsername(userName);

        if(Objects.nonNull(username)){
            return username;
        } else{
            throw new UsernameNotFoundException(String.format("Username %s not found", userName));
        }
    }
}
