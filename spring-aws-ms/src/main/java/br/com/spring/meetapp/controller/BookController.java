package br.com.spring.meetapp.controller;

import br.com.spring.meetapp.converter.DozerConverter;
import br.com.spring.meetapp.service.BookService;
import br.com.spring.meetapp.model.Book;
import br.com.spring.meetapp.vo.BookVO;
import br.com.spring.meetapp.vo.PersonVO;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/book")
@ApiResponse(description = "Book Endpoint")
@CrossOrigin
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public HttpEntity<BookVO> findById(@PathVariable("id") Long id){
        BookVO bookVO = DozerConverter.parseObject(bookService.findById(id), BookVO.class);
        bookVO.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        return ResponseEntity.ok(bookVO);
    }

    @GetMapping
    public ResponseEntity<List<BookVO>> findAll(){
        List<BookVO> books = bookService.findAll();
        books.forEach(book -> book.add((linkTo(methodOn(PersonController.class).findById(book.getKey())).withSelfRel())));
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PostMapping(consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<BookVO> add(@RequestBody Book book){
        BookVO bookVO = bookService.create(book);
        bookVO.add(linkTo(methodOn(BookController.class).add(book)).withSelfRel());
        return new ResponseEntity<>(bookVO, HttpStatus.CREATED);
    }

    @PutMapping(consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<BookVO> updatePerson(@RequestBody BookVO bookVO){
        Book book = bookService.update(bookVO);
        return new ResponseEntity(DozerConverter.parseObject(book, PersonVO.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable ("id") Long id){
        bookService.delete(id);
    }
}
