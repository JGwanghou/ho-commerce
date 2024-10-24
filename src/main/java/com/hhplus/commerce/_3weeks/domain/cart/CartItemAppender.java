package com.hhplus.commerce._3weeks.domain.cart;

import com.hhplus.commerce._3weeks.infra.cart.cartitem.CartItemEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CartItemAppender {
    private final CartItemRepository cartItemRepository;

    public CartItemEntity addItem(long cartId, long productId, long quantity) {
        return cartItemRepository.save(cartId, productId, quantity);
    }
}
