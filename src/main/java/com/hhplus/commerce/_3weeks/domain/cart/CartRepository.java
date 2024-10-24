package com.hhplus.commerce._3weeks.domain.cart;

import com.hhplus.commerce._3weeks.infra.cart.CartEntity;
import com.hhplus.commerce._3weeks.infra.cart.cartitem.CartItemEntity;

import java.util.Optional;

public interface CartRepository {
    CartEntity readIsNullCreate(Long userId);

}
