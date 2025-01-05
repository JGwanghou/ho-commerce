package com.gwangho.commerce.app.domain.order.infrastructure;

import com.gwangho.commerce.app.domain.order.Order;
import com.gwangho.commerce.app.domain.order.repository.OrderStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderCoreStoreRepository implements OrderStoreRepository {
    private final OrderJpaRepository orderJpaRepository;

    @Override
    public Order save(Order entity) {
        return orderJpaRepository.save(entity);
    }
}
