package com.gwangho.commerce.app.domain.order.repository;

import com.gwangho.commerce.app.domain.order.OrderItem;

import java.util.List;

public interface OrderItemStoreRepository {
    OrderItem save(OrderItem entity);
    List<OrderItem> saveAll(List<OrderItem> entities);
}
