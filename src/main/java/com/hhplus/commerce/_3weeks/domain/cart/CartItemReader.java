package com.hhplus.commerce._3weeks.domain.cart;

import com.hhplus.commerce._3weeks.domain.cart.cartItem.CartItem;
import com.hhplus.commerce._3weeks.infra.cart.cartitem.CartItemEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CartItemReader {
    private final CartItemRepository cartItemRepository;

    public List<CartItemEntity> findCartItems(Long cartId) {
        return cartItemRepository.findCartItems(cartId);
    }
}
