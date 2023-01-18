package br.com.books.books;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RestResource(rel = "code", path = "code")
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {
    List<Book> findAllByCode(@Param("code") String code, Pageable pageable);
}
