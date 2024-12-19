package com.gwangho.commerce.app.domain.order.infrastructure;

import com.gwangho.commerce.app.domain.order.Order;
import com.gwangho.commerce.app.domain.order.repository.OrderReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderCoreReaderRepository implements OrderReaderRepository {
    private final OrderJpaRepository orderJpaRepository;

    @Override
    public Order findByIdOrOrderNotFoundThrow(Long orderId) {
        return null;
    }
}
