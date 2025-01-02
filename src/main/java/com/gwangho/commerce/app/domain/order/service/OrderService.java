package com.gwangho.commerce.app.domain.order.service;

import com.gwangho.commerce.app.api.order.OrderResponse;
import com.gwangho.commerce.app.domain.order.Order;
import com.gwangho.commerce.app.domain.order.OrderItem;
import com.gwangho.commerce.app.domain.order.enums.OrderStatus;
import com.gwangho.commerce.app.domain.order.event.OrderedCreatedEvent;
import com.gwangho.commerce.app.domain.order.event.OrderedEventPublisher;
import com.gwangho.commerce.app.domain.order.repository.OrderItemReaderRepository;
import com.gwangho.commerce.app.domain.order.repository.OrderItemStoreRepository;
import com.gwangho.commerce.app.domain.order.repository.OrderReaderRepository;
import com.gwangho.commerce.app.domain.order.repository.OrderStoreRepository;
import com.gwangho.commerce.app.domain.product.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public OrderResponse order(OrderCommand.CreateOrder command, List<Product> productActualInfo) {
        /**
         *  command: C로 정의, productActualInfo: P로 정의
         *
         *  클라이언트에서 보낸 C의 totalAmount는 변조 가능성이 있다.
         *  따라서, 주문된 상품의 실제 정보(가격,상품명)가 담긴 P를 기준으로 C의 productId와 count로 가격책정
         */

        BigDecimal totalPrice = calcTotalPrice(command, productActualInfo);

        Order order = Order.builder()
                .userId(command.userId())
                .totalPrice(totalPrice)
                .status(OrderStatus.READY)
                .build();
        Order orderInfo = orderStoreRepository.save(order);

        List<OrderItem> orderItems = command.createOrderItems().stream()
                .map(item -> OrderItem.builder()
                        .orderId(orderInfo.getId())
                        .productId(item.productId())
                        .orderPrice(BigDecimal.valueOf(2000))
                        .count(item.count())
                        .build())
                .toList();
        orderItemStoreRepository.saveAll(orderItems);

        log.info("----- 주문 ID: {}, 주문서 저장 이벤트 발행 start -----", order.getId());
        orderEventPublisher.success(new OrderedCreatedEvent(order, orderItems));
        log.info("----- 주문 ID: {}, 주문서 저장 이벤트 발행 end -----", order.getId());
        return new OrderResponse(order.getId(), order.getTotalPrice());
    }

    public BigDecimal calcTotalPrice(OrderCommand.CreateOrder command, List<Product> productActualInfo) {
        Map<Long, BigDecimal> productPriceMap = productActualInfo.stream()
                .collect(Collectors.toMap(Product::getId, Product::getPrice));

        BigDecimal calculatedTotalAmount = command.createOrderItems().stream()
                .map(orderItem -> {
                    BigDecimal productPrice = productPriceMap.get(orderItem.productId());
                    
                    // 개수, 가격을 곱하여 해당 상품의 총 금액 계산
                    return productPrice.multiply(BigDecimal.valueOf(orderItem.count()));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        return calculatedTotalAmount;
    }
}
