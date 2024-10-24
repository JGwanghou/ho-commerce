package com.hhplus.commerce._3weeks.domain.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CartItemRemover {
    private final CartItemRepository cartItemRepository;

    public void remove(List<Long> productIds) {
        cartItemRepository.deleteByProductId(productIds);
    }
}
