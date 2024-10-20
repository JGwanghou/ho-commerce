package com.hhplus.commerce._3weeks.domain.order;

import com.hhplus.commerce._3weeks.domain.user.User;
import com.hhplus.commerce._3weeks.infra.order.OrderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderProcessor {
    private final OrderRepository orderRepository;

    public OrderEntity processOrder(Long userId) {
        return orderRepository.saveUserId(userId);
    }
}
