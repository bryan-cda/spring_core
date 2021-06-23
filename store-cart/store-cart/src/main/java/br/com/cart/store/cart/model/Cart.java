package br.com.cart.store.cart.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.ArrayList;
import java.util.List;

@RedisHash("cart")
@Data
public class Cart {
    @Id
    private Long id;
    private Long code;
    private List<Item> itemList = new ArrayList<>();
}
