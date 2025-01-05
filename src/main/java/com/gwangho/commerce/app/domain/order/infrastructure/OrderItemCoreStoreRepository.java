package com.gwangho.commerce.app.domain.order.infrastructure;

import com.gwangho.commerce.app.domain.order.OrderItem;
import com.gwangho.commerce.app.domain.order.repository.OrderItemStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderItemCoreStoreRepository implements OrderItemStoreRepository {
    private final OrderItemJpaRepository orderItemJpaRepository;

    @Override
    public OrderItem save(OrderItem entity) {
        return null;
    }

    @Override
    public List<OrderItem> saveAll(List<OrderItem> entities) {
        return orderItemJpaRepository.saveAll(entities);
    }
}
