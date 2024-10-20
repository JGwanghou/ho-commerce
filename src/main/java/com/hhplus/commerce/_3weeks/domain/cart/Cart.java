package com.hhplus.commerce._3weeks.domain.cart;

import lombok.Getter;

@Getter
public class Cart {
    private Long id;
    private Long user_id;

    public Cart(Long id, Long userId) {
        this.id = id;
        this.user_id = userId;
    }
}
