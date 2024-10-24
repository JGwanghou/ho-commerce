package com.hhplus.commerce._3weeks.domain.product;

import lombok.Getter;

@Getter
public class Product {
    private Long id;
    private String name;
    private int price;

    // JOIN 상품조회 추가필드
    private int stock;

    // JOIN 인기상품 판매수량 추가필드
    private int sale_count;

    public Product(String name, int sale_count) {
        this.name = name;
        this.sale_count = sale_count;
    }

    public Product(Long id, String name, int price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }


}
