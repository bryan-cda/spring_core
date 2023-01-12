package br.com.spring.webflux.repository;

import br.com.spring.webflux.domain.Quote;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface QuoteRepository extends PagingAndSortingRepository<Quote, Long> {
    @RestResource(rel = "quotes", path = "quotes")
    List<Quote> findAllBySymbol(@Param("quote") String quote, Pageable pageable);
}
