package com.hhplus.commerce._3weeks.domain.order;

import com.hhplus.commerce._3weeks.domain.product.Product;
import com.hhplus.commerce._3weeks.domain.user.User;
import com.hhplus.commerce._3weeks.infra.order.OrderEntity;

import java.util.List;

public interface OrderRepository {
    OrderEntity saveUserId(Long userId);
}
