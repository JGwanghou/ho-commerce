package com.hhplus.commerce._3weeks.domain.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CartAppender {
    private final CartRepository cartRepository;


}
