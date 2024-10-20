package com.hhplus.commerce._3weeks.infra.order.orderItem;

import com.hhplus.commerce._3weeks.domain.order.OrderRepository;
import com.hhplus.commerce._3weeks.domain.order.orderItem.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderItemRepositoryImpl implements OrderItemRepository {
    private final OrderItemJpaRepository orderItemJpaRepository;

    public List<OrderItemEntity> saveItems(List<OrderItemEntity> orderItems) {
        return orderItemJpaRepository.saveAll(orderItems);

    }
}
