package br.com.catalog.store.store.repository;

import br.com.catalog.store.store.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
