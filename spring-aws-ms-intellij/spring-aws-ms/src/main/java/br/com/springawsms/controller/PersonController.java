package br.com.springawsms.controller;

import br.com.springawsms.model.Person;
import br.com.springawsms.service.PersonService;
import br.com.springawsms.vo.PersonVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<PersonVO>> findAll(){
        return new ResponseEntity<>(personService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PersonVO> add(@RequestBody Person person){
        return new ResponseEntity<>(personService.create(person), HttpStatus.CREATED);
    }

    @PutMapping
    public void updatePerson(@RequestBody PersonVO personVO){
        personService.update(personVO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable ("id") Long id){
        personService.delete(id);
    }
}
