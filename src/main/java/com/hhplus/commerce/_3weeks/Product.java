package com.hhplus.commerce._3weeks;

import lombok.Getter;

@Getter
public class Product {
    private int id;
    private String name;
    private int price;
    private int quantity;

    public Product(int id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public Product(int id, String name, int price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Product stockDecrease(int id, int buyQuantity) {
        if(this.quantity - buyQuantity < 0) {
            throw new RuntimeException("재고가 부족한 상품이 있습니다 id : " + id);
        }

        this.quantity -= buyQuantity;
        return this;
    }
}
