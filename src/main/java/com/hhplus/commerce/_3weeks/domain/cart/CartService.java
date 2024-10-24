package com.hhplus.commerce._3weeks.domain.cart;

import com.hhplus.commerce._3weeks.domain.cart.cartItem.CartItem;
import com.hhplus.commerce._3weeks.infra.cart.CartEntity;
import com.hhplus.commerce._3weeks.infra.cart.cartitem.CartItemEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartReader cartReader;
    private final CartItemReader cartItemReader;
    private final CartItemAppender cartItemAppender;
    private final CartItemRemover cartItemRemover;

    public CartEntity readIsNullCreate(long userId) {
        return cartReader.readIsNullCreate(userId);
    }

    public CartItemEntity addItem(long cartId, long productId, long quantity) {
        return cartItemAppender.addItem(cartId, productId, quantity);
    }

    public List<CartItemEntity> findCartItems(long cartId) {
        return cartItemReader.findCartItems(cartId);
    }

    @Transactional
    public void remove(List<Long> productIds) {
        cartItemRemover.remove(productIds);
    }
}
