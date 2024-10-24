package com.hhplus.commerce._3weeks.domain.cart;

import com.hhplus.commerce._3weeks.infra.cart.CartEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CartReader {
    private final CartRepository cartRepository;

    public CartEntity readIsNullCreate(Long userId) {
        return cartRepository.readIsNullCreate(userId);
    }
}
