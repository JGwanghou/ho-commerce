package com.hhplus.commerce._3weeks.infra.cart.cartitem;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemJpaRepository extends JpaRepository<CartItemEntity, Long> {
    List<CartItemEntity> findByCartId(Long cartId);
    void deleteByProductIdIn(List<Long> productIds);
}
