package br.com.cart.store.cart.service;

import br.com.cart.store.cart.model.Cart;
import br.com.cart.store.cart.model.Item;
import br.com.cart.store.cart.repository.CartRepository;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@Service
public class CartService {
    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository){
        this.cartRepository = cartRepository;
    }

    public Cart addCart(Long code, Item item){
        Cart currentCart = cartRepository.findByCode(code);

        if(nonNull(currentCart)){
            currentCart.getItemList().add(item);
        }

        currentCart = new Cart();
        currentCart.setId(System.currentTimeMillis());
        currentCart.setCode(System.currentTimeMillis());
        currentCart.getItemList().add(item);

        return cartRepository.save(currentCart);
    }

    public Cart findByCode(Long code){
        return cartRepository.findByCode(code);
    }
}
