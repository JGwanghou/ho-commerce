package com.hhplus.commerce._3weeks.domain.order;

import com.hhplus.commerce._3weeks.api.dto.request.OrderRequest;
import com.hhplus.commerce._3weeks.domain.product.Product;
import com.hhplus.commerce._3weeks.domain.user.User;
import com.hhplus.commerce._3weeks.infra.order.OrderEntity;
import com.hhplus.commerce._3weeks.infra.order.orderItem.OrderItemEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderItemAppender orderItemAppender;
    private final OrderProcessor orderProcessor;

    public OrderEntity serviceOrder(Long userId, OrderRequest request) {
        OrderEntity order = orderProcessor.processOrder(userId);

        List<OrderItemEntity> orderItems = orderItemAppender.create(order, request.getProducts());

        return order;
    }
}
