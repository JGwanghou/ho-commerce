package com.hhplus.commerce._3weeks.api.dto.request;

import com.hhplus.commerce._3weeks.domain.cart.cartItem.CartItem;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class CartItemRequest {
    private Long user_id;
    private CartItem item;
}
