package com.gwangho.commerce.app.domain.order.repository;

import com.gwangho.commerce.app.domain.order.Order;

public interface OrderStoreRepository {
    Order save(Order entity);
}
