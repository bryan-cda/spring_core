package br.com.catalog.store.store.model;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "product", type="catalog")
public class Product {
    private Long id;
    private String name;
    private String description;
    private Integer count;
}
