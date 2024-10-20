package com.hhplus.commerce._3weeks.domain.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CartReader {
    private final CartRepository cartRepository;

    public boolean isCartExists(Long userId) {
        return cartRepository.findByUserId(userId);
    }
}
