package com.hhplus.commerce._3weeks.domain.product;

import lombok.Getter;

@Getter
public class Product {
    private Long id;
    private String name;
    private int price;
    private int stock;

    public Product(Long id, String name, int price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
}
