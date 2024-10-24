package com.hhplus.commerce._3weeks.infra.cart;

import com.hhplus.commerce._3weeks.domain.cart.Cart;
import com.hhplus.commerce._3weeks.domain.cart.CartRepository;
import com.hhplus.commerce._3weeks.domain.cart.cartItem.CartItem;
import com.hhplus.commerce._3weeks.infra.cart.cartitem.CartItemEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CartRepositoryImpl implements CartRepository {

    private final CartJpaRepository cartJpaRepository;

    @Override
    public CartEntity readIsNullCreate(Long userId) {
        return cartJpaRepository.findByUserId(userId)
                .orElseGet(
                        () -> cartJpaRepository.save(new CartEntity(userId))
                );
    }

}
