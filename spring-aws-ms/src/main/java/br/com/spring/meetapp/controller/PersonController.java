package br.com.spring.meetapp.controller;

import br.com.spring.meetapp.converter.DozerConverter;
import br.com.spring.meetapp.model.Book;
import br.com.spring.meetapp.model.Person;
import br.com.spring.meetapp.service.PersonService;
import br.com.spring.meetapp.vo.PersonVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService){
        this.personService = personService;
    }

    @Operation(summary = "Get a book by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content) })
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

    @GetMapping("find-all")
    public ResponseEntity<Page<PersonVO>> findAll(@RequestParam (value = "page", defaultValue = "0") int page,
                                                  @RequestParam (value = "limit", defaultValue = "20")int limit,
                                                  @RequestParam (value = "direction", defaultValue = "asc")String direction){
        var sortDirection = direction.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;

        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "firstName"));

        Page<PersonVO> persons = personService.findAll(pageable);
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    @PostMapping(consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<PersonVO> add(@RequestBody PersonVO personVO){
        Person person = DozerConverter.parseObject(personVO, Person.class);
        PersonVO personCreated = personService.create(person);
        personVO.add(linkTo(methodOn(PersonController.class).add(personCreated)).withSelfRel());
        return new ResponseEntity<>(personVO, HttpStatus.CREATED);
    }

    @PutMapping(consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<PersonVO> updatePerson(@RequestBody PersonVO personVO){
        Person update = personService.update(personVO);
        return new ResponseEntity<>(DozerConverter.parseObject(update, PersonVO.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable ("id") Long id){
        personService.delete(id);
    }
}
