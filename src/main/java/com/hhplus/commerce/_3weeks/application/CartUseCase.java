package com.hhplus.commerce._3weeks.application;

import com.hhplus.commerce._3weeks.domain.cart.cartItem.CartItem;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartUseCase {



    public void addCart(Long userId, List<CartItem> cartItems) {
        유저서비스.유저조회(userId)

        카트서비스.카트생성

        프로덕트서비스.담은상품의수량재고보다작은지

        카트서비스->item.아이템저장
    }
}
