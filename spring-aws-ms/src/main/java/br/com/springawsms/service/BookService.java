package br.com.springawsms.service;

import br.com.springawsms.converter.DozerConverter;
import br.com.springawsms.exeption.EntityNotFoundException;
import br.com.springawsms.model.Book;
import br.com.springawsms.repository.BookRepository;
import br.com.springawsms.vo.BookVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public BookVO create(Book book){
        log.info(String.format("Trying to save book %s", book));
        return DozerConverter.parseObject(bookRepository.save(book), BookVO.class);
    }

    public Book update(BookVO bookVO){
        Book bookFromDatabase = bookRepository
                .findById(bookVO.getKey()).orElseThrow(() ->
                        new EntityNotFoundException(
                                String.format("Book not found %s not found", bookVO)));
        bookFromDatabase.setAuthor(bookVO.getAuthor());
        bookFromDatabase.setTitle(bookVO.getTitle());
        bookFromDatabase.setLaunchDate(bookVO.getLaunchDate());
        bookFromDatabase.setPrice(bookVO.getPrice());
        return bookRepository.save(bookFromDatabase);
    }

    public Book findById(Long id){
        log.info(String.format("Searching for book with id %s", id));
        return bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Person not found for id %s", id)));
    }

    public List<BookVO> findAll(){
        log.info(String.format("Searching for all books"));
        return DozerConverter.parseListObjects(bookRepository.findAll(), BookVO.class);
    }

    public void delete(Long id) {
        log.info(String.format(String.format("Trying to delete book for id %s", id)));

        Book book = bookRepository
                .findById(id).orElseThrow(() ->
                            new EntityNotFoundException(String.format("Book not found for id %s", id)));
        bookRepository.deleteById(book.getId());
    }
}
