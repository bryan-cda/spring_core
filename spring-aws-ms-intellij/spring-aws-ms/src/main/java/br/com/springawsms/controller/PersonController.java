package br.com.springawsms.controller;

import br.com.springawsms.converter.DozerConverter;
import br.com.springawsms.model.Person;
import br.com.springawsms.service.PersonService;
import br.com.springawsms.vo.PersonVO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/v1/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService){
        this.personService = personService;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public HttpEntity<PersonVO> findById(@PathVariable ("id") Long id){
        PersonVO personVO = DozerConverter.parseObject(personService.findById(id), PersonVO.class);
        personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return ResponseEntity.ok(personVO);
    }

    @GetMapping
    public ResponseEntity<List<PersonVO>> findAll(){
        List<PersonVO> persons = personService.findAll();
        persons.stream().forEach(person -> person.add((linkTo(methodOn(PersonController.class).findById(person.getKey())).withSelfRel())));
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PersonVO> add(@RequestBody Person person){
        PersonVO personVO = personService.create(person);
        personVO.add(linkTo(methodOn(PersonController.class).add(person)).withSelfRel());
        return new ResponseEntity<>(personVO, HttpStatus.CREATED);
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
