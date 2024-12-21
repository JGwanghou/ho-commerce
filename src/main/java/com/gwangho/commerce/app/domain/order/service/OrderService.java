package com.gwangho.commerce.app.domain.order.service;

import com.gwangho.commerce.app.domain.order.event.OrderedCreatedEvent;
import com.gwangho.commerce.app.domain.order.Order;
import com.gwangho.commerce.app.domain.order.OrderItem;
import com.gwangho.commerce.app.domain.order.enums.OrderStatus;
import com.gwangho.commerce.app.domain.order.event.OrderedEventPublisher;
import com.gwangho.commerce.app.domain.order.repository.OrderItemReaderRepository;
import com.gwangho.commerce.app.domain.order.repository.OrderItemStoreRepository;
import com.gwangho.commerce.app.domain.order.repository.OrderReaderRepository;
import com.gwangho.commerce.app.domain.order.repository.OrderStoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderReaderRepository orderReaderRepository;
    private final OrderStoreRepository orderStoreRepository;

    private final OrderItemReaderRepository orderItemReaderRepository;
    private final OrderItemStoreRepository orderItemStoreRepository;
    private final OrderedEventPublisher orderEventPublisher;

    @Transactional
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

        log.info("----- 주문 ID: {}, 주문서 저장 이벤트 발행 start -----", order.getId());
        orderEventPublisher.success(new OrderedCreatedEvent(order, orderItems));
        log.info("----- 주문 ID: {}, 주문서 저장 이벤트 발행 end -----", order.getId());
        return order.getId();
    }
}
