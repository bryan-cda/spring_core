package br.com.cart.store.cart.controller;

import br.com.cart.store.cart.model.Cart;
import br.com.cart.store.cart.model.Item;
import br.com.cart.store.cart.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.util.Objects.nonNull;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/v1/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService){
        this.cartService = cartService;
    }

    @PostMapping("/{code}")
    public ResponseEntity addCart(@PathVariable Long code, @RequestBody Item item){
        return new ResponseEntity(cartService.addCart(code, item), OK);
    }
}
