package com.gwangho.commerce.app.domain.order.service;

import com.gwangho.commerce.app.domain.order.event.OrderCreatedEvent;
import com.gwangho.commerce.app.domain.order.Order;
import com.gwangho.commerce.app.domain.order.OrderItem;
import com.gwangho.commerce.app.domain.order.enums.OrderStatus;
import com.gwangho.commerce.app.domain.order.event.OrderEventPublisher;
import com.gwangho.commerce.app.domain.order.repository.OrderItemReaderRepository;
import com.gwangho.commerce.app.domain.order.repository.OrderItemStoreRepository;
import com.gwangho.commerce.app.domain.order.repository.OrderReaderRepository;
import com.gwangho.commerce.app.domain.order.repository.OrderStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderReaderRepository orderReaderRepository;
    private final OrderStoreRepository orderStoreRepository;

    private final OrderItemReaderRepository orderItemReaderRepository;
    private final OrderItemStoreRepository orderItemStoreRepository;
    private final OrderEventPublisher orderEventPublisher;

    public Long order(OrderCommand.CreateOrder command) {
        // order
        Order order = Order.builder()
                .userId(command.userId())
                .totalPrice(command.paymentAmount())
                .status(OrderStatus.READY)
                .build();

        Order orderInfo = orderStoreRepository.save(order);

        // orderItem
        List<OrderItem> orderItems = command.createOrderItems().stream()
                .map(item -> OrderItem.builder()
                        .orderId(orderInfo.getId())
                        .productId(item.productId())
                        .count(item.count())
                        .build())
                .toList();
        orderItemStoreRepository.saveAll(orderItems);

        orderEventPublisher.success(new OrderCreatedEvent(order, orderItems));
        return order.getId();
    }
}
