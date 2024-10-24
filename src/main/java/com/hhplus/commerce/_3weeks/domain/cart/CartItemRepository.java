package com.hhplus.commerce._3weeks.domain.cart;

import com.hhplus.commerce._3weeks.domain.cart.cartItem.CartItem;
import com.hhplus.commerce._3weeks.infra.cart.cartitem.CartItemEntity;

import java.util.List;

public interface CartItemRepository {
    CartItemEntity save(long cartId, long productId, long quantity);

    List<CartItemEntity> findCartItems(Long cartId);
    void deleteByProductId(List<Long> productIds);
}
