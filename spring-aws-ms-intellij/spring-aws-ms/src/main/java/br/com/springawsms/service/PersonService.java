package br.com.springawsms.service;

import br.com.springawsms.exeption.PersonNotFoundException;
import br.com.springawsms.model.Person;
import br.com.springawsms.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public Person create(Person person){
        log.info(String.format("Trying to save person %s", person));
        return  personRepository.save(person);
    }

    public void update(Person person){
        Person personFromDatabase = personRepository.findById(person.getId()).orElseThrow(() -> new PersonNotFoundException(String.format("Person not found %s not found", person)));
        personFromDatabase.setFirstName(person.getFirstName());
        personFromDatabase.setAddress(person.getAddress());
        personFromDatabase.setGender(person.getGender());
        personRepository.save(personFromDatabase);

    }

    public Person findById(Long id){
        Optional<Person> person = personRepository.findById(id);

        log.info(String.format("Searching for person with id %s", id));

        return personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(String.format("Person not found for id %s", id)));
    }

    public List<Person> findAll(){
        log.info(String.format("Searching for all persons"));
        return personRepository.findAll();
    }

    public void delete(Long id) {
        personRepository.deleteById(id);
    }
}
