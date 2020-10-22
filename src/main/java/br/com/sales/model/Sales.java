package br.com.sales.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Sales implements Serializable {
    private String product;
    private Float price;
    private LocalDate date;
    private String store;

    public Sales() {
    }

    public Sales(String product, Float price, LocalDate date, String store) {
        this.product = product;
        this.price = price;
        this.date = date;
        this.store = store;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }
}
