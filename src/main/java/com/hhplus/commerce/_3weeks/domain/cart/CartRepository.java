package com.hhplus.commerce._3weeks.domain.cart;

import com.hhplus.commerce._3weeks.infra.cart.CartEntity;

import java.util.Optional;

public interface CartRepository {
    boolean findByUserId(Long userId);
}
