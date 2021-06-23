package br.com.cart.store.cart.repository;

import br.com.cart.store.cart.model.Cart;
import br.com.cart.store.cart.model.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {
    Cart findByCode(Long code);
}
