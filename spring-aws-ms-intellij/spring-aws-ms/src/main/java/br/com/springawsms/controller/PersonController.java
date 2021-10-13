package br.com.springawsms.controller;

import br.com.springawsms.model.Person;
import br.com.springawsms.service.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService){
        this.personService = personService;
    }

    @GetMapping("{/id}")
    public Optional<Person> findById(@PathVariable ("id") Long id){
        return personService.findById(id);
    }

    @GetMapping("{/id}")
    public List<Person> findAll(){
        return personService.findAll();
    }
}
