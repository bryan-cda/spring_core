package br.com.springawsms.service;

import br.com.springawsms.exeption.PersonNotFoundException;
import br.com.springawsms.model.Person;
import br.com.springawsms.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public Optional<Person> findById(Long id){
        Optional<Person> person = personRepository.findById(id);

        if(!person.isPresent()){
            throw new PersonNotFoundException(String.format("Person not found for id %s", id));
        }
        return person;
    }

    public List<Person> findAll(){
        return personRepository.findAll();
    }

}
