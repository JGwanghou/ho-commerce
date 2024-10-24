package com.hhplus.commerce._3weeks.domain.cart;

import com.hhplus.commerce._3weeks.domain.cart.cartItem.CartItem;
import com.hhplus.commerce._3weeks.domain.product.Product;
import com.hhplus.commerce._3weeks.infra.cart.CartEntity;
import com.hhplus.commerce._3weeks.infra.cart.cartitem.CartItemEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CartServiceIntegrationTest {

    @Autowired
    private CartService cartService;

    @Test
    void 장바구니_조회() {
        final long userId = 5L;
        cartService.readIsNullCreate(userId);

    }

    @Test
    void 장바구니_생성() {
        final long userId = 10L;
        CartEntity cart = cartService.readIsNullCreate(userId);

        assertNotNull(cart);
    }

    @Test
    @DisplayName("사용자는 장바구니에 상품을 하나씩만 담을수있다.")
    void 장바구니_아이템추가() {
        final Product testProd1 = new Product(1L, "딸기", 2000, 100);
        final long userId = 20L;

        CartEntity cart = cartService.readIsNullCreate(userId);
        CartItemEntity cartItemEntity = cartService.addItem(cart.getId(), testProd1.getId(), 4);

        assertEquals(1L, cartItemEntity.getCartId());
        assertEquals(1L, cartItemEntity.getProductId());
        assertEquals(4, cartItemEntity.getQuantity());
    }

}