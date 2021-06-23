package br.com.cart.store.cart.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.math.BigDecimal;

@RedisHash("item")
@Data
public class Item {
    @Id
    private Long id;
    private BigDecimal amount;
}
