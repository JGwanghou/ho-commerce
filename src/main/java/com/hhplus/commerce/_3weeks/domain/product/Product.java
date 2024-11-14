package com.hhplus.commerce._3weeks.domain.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long id;
    private String name;
    private int price;

    // int -> Integer로 변경하여 null 허용
    private Integer stock;
    private Long sale_count;

    // findByIdWithStock 용 생성자
    public Product(Long id, String name, int price, Integer stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.sale_count = null;
    }

    // findProductPopulars 용 생성자
    public Product(String name, Long quantity) {
        this.id = null;
        this.name = name;
        this.price = 0;
        this.stock = 0;
        this.sale_count = quantity;
    }
}
