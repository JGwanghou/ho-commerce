package com.hhplus.commerce._3weeks;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductStock {
    Long id;
    Long stock;

    public ProductStock decreaseStock(Long amount) {
        this.stock -= amount;
        return this;
    }
}
