package br.com.springawsms.controller;

import br.com.springawsms.model.Person;
import br.com.springawsms.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService){
        this.personService = personService;
    }

    @GetMapping("/{id}")
    public Person findById(@PathVariable ("id") Long id){
        return personService.findById(id);
    }

    @GetMapping
    public List<Person> findAll(){
        return personService.findAll();
    }

    @PostMapping
    public Person add(@RequestBody Person person){
        return personService.create(person);
    }

    @PutMapping
    public void updatePerson(@RequestBody Person person){
        personService.update(person);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable ("id") Long id){
        personService.delete(id);
    }
}
