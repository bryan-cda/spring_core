package br.com.springawsms.service;

import br.com.springawsms.repository.PersonRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }


}
