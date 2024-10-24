package com.hhplus.commerce._3weeks.application;

import com.hhplus.commerce._3weeks.domain.cart.CartService;
import com.hhplus.commerce._3weeks.domain.cart.cartItem.CartItem;
import com.hhplus.commerce._3weeks.domain.product.ProductService;
import com.hhplus.commerce._3weeks.domain.user.User;
import com.hhplus.commerce._3weeks.domain.user.UserService;
import com.hhplus.commerce._3weeks.infra.cart.CartEntity;
import com.hhplus.commerce._3weeks.infra.cart.cartitem.CartItemEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CartUseCase {
    private final CartService cartService;
    private final ProductService productService;

    public List<CartItemEntity> findCartProducts(Long userId) {
        CartEntity cart = cartService.readIsNullCreate(userId);
        return cartService.findCartItems(cart.getId());
    }

    public CartItemEntity addCart(Long userId, Long productId, Long quantity) {
        productService.readCartProductDetailStockCheck(productId, quantity);

//      Cart 생성 및 CartItem 저장
        CartEntity cart = cartService.readIsNullCreate(userId);
        return cartService.addItem(cart.getId(), productId, quantity);
    }

    public void remove(List<Long> productIds) {
        cartService.remove(productIds);
    }
}
