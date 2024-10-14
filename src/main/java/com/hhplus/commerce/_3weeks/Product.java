package com.hhplus.commerce._3weeks;

import lombok.Getter;

@Getter
public class Product {
    private Long id;
    private String name;
    private Long price;
    private Long stock;


    public Product(Long id, String name, Long price, Long stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public Product(String name, Long price) {
        this.name = name;
        this.price = price;
    }

    public Product stockDecrease(int id, int buyQuantity) {
        if(this.stock - buyQuantity < 0) {
            throw new RuntimeException("재고가 부족한 상품이 있습니다 id : " + id);
        }

        this.stock -= buyQuantity;
        return this;
    }

}
