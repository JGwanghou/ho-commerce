package com.hhplus.commerce._3weeks.infra.cart;

import com.hhplus.commerce._3weeks.domain.cart.Cart;
import com.hhplus.commerce._3weeks.domain.cart.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CartRepositoryImpl implements CartRepository {

    private final CartJpaRepository cartJpaRepository;

    @Override
    public boolean findByUserId(Long userId) {
        return cartJpaRepository.findByUserId(userId).isEmpty();
    }
}
