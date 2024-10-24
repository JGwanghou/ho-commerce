package com.hhplus.commerce._3weeks.infra.cart.cartitem;

import com.hhplus.commerce._3weeks.domain.cart.CartItemRepository;
import com.hhplus.commerce._3weeks.domain.cart.cartItem.CartItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CartItemRepositoryImpl implements CartItemRepository {

    private final CartItemJpaRepository cartItemJpaRepository;

    @Override
    public CartItemEntity save(long cartId, long productId, long quantity) {
        return cartItemJpaRepository.save(new CartItemEntity(cartId, productId, quantity));
    }

    @Override
    public List<CartItemEntity> findCartItems(Long cartId) {
        return cartItemJpaRepository.findByCartId(cartId);
    }

    @Override
    public void deleteByProductId(List<Long> productIds) {
        cartItemJpaRepository.deleteByProductIdIn(productIds);
    }
}
