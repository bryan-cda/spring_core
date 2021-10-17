package br.com.springawsms.service;

import br.com.springawsms.converter.DozerConverter;
import br.com.springawsms.exeption.PersonNotFoundException;
import br.com.springawsms.model.Person;
import br.com.springawsms.repository.PersonRepository;
import br.com.springawsms.vo.PersonVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public PersonVO create(Person person){
        log.info(String.format("Trying to save person %s", person));
        return DozerConverter.parseObject(personRepository.save(person), PersonVO.class);
    }

    public void update(PersonVO personVO){
        Person personFromDatabase = personRepository
                .findById(personVO.getId()).orElseThrow(() ->
                        new PersonNotFoundException(
                                String.format("Person not found %s not found", personVO)));
        personFromDatabase.setFirstName(personVO.getFirstName());
        personFromDatabase.setAddress(personVO.getAddress());
        personFromDatabase.setGender(personVO.getGender());
        personRepository.save(personFromDatabase);
    }

    public Person findById(Long id){
        log.info(String.format("Searching for person with id %s", id));

        return personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(String.format("Person not found for id %s", id)));
    }

    public List<PersonVO> findAll(){
        log.info(String.format("Searching for all persons"));
        return DozerConverter.parseListObjects(personRepository.findAll(), PersonVO.class);
    }

    public void delete(Long id) {
        log.info(String.format(String.format("Trying to delete person for id %s", id)));

        Person person = personRepository
                .findById(id).orElseThrow(() ->
                        new PersonNotFoundException(String.format("Person not found for id %s", id)));
        personRepository.deleteById(person.getId());
    }
}
