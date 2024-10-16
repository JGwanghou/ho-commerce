package com.hhplus.commerce._3weeks.domain.cart;

import com.hhplus.commerce._3weeks.infra.cart.CartEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartReader cartReader;
    private final CartAppender cartAppender;

    public boolean readCart(Long userId) {
        return cartReader.isCartExists(userId);
    }

//    public Cart getCartAppender() {
//        readCart()
//        return cartAppender;
//    }
}
