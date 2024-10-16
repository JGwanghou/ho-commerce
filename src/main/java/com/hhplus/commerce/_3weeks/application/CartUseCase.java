package com.hhplus.commerce._3weeks.application;

import com.hhplus.commerce._3weeks.domain.cart.CartService;
import com.hhplus.commerce._3weeks.domain.cart.cartItem.CartItem;
import com.hhplus.commerce._3weeks.domain.product.ProductService;
import com.hhplus.commerce._3weeks.domain.user.User;
import com.hhplus.commerce._3weeks.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CartUseCase {
    private final UserService userService;
    private final CartService cartService;
    private final ProductService productService;

    public void addCart(Long userId, List<CartItem> cartItems) {
        User userInfo = userService.getUserInfo(userId);

//        cartService.카트생성
//
//        프로덕트서비스.담은상품의수량재고보다작은지
//
//        카트서비스->item.아이템저장
    }
}
