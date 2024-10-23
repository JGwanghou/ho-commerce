package com.hhplus.commerce._3weeks.domain.order;

import com.hhplus.commerce._3weeks.api.dto.request.OrderProductsRequest;
import com.hhplus.commerce._3weeks.api.dto.request.OrderRequest;
import com.hhplus.commerce._3weeks.domain.product.Product;
import com.hhplus.commerce._3weeks.domain.user.User;
import com.hhplus.commerce._3weeks.infra.order.OrderEntity;
import com.hhplus.commerce._3weeks.infra.order.orderItem.OrderItemEntity;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderProcessor orderProcessor;

    @Mock
    private OrderItemAppender orderItemAppender;

    @InjectMocks
    private OrderService orderService;

    @Test
    void 주문테이블_저장() {
        User user = new User(30L, "김테스트", 2000L);
        OrderEntity order = new OrderEntity(356L, 30L);

        List<Product> products = List.of(
                new Product(1L, "토마토", 2000, 10),
                new Product(2L, "계란", 2000, 20)
        );

        List<OrderProductsRequest> orderProductsRequests = List.of(
                new OrderProductsRequest(1L, 2),
                new OrderProductsRequest(2L, 3)
        );

        List<OrderItemEntity> items = List.of(
                new OrderItemEntity(order.getId(), 1L, 2),
                new OrderItemEntity(order.getId(), 2L, 3)
        );

        OrderRequest orderRequest = new OrderRequest(user.getId(), orderProductsRequests, 10000L);

        when(orderProcessor.processOrder(30L)).thenReturn(order);
        when(orderItemAppender.create(order, orderRequest.getProducts())).thenReturn(items);

        OrderEntity result = orderService.serviceOrder(user.getId(), orderRequest);

        assertEquals(order.getId(), result.getId());
    }
}