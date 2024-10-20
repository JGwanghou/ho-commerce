package com.hhplus.commerce._3weeks.application;

import com.hhplus.commerce._3weeks.api.dto.request.OrderProductsRequest;
import com.hhplus.commerce._3weeks.api.dto.request.OrderRequest;
import com.hhplus.commerce._3weeks.domain.order.OrderService;
import com.hhplus.commerce._3weeks.domain.product.Product;
import com.hhplus.commerce._3weeks.domain.product.ProductService;
import com.hhplus.commerce._3weeks.domain.user.UserService;
import com.hhplus.commerce._3weeks.infra.order.OrderEntity;
import com.hhplus.commerce._3weeks.infra.order.orderItem.OrderItemEntity;
import com.hhplus.commerce._3weeks.infra.user.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderUseCaseTest {

    @Autowired
    private OrderUseCase orderUseCase;

    @Test
    void 주문성공_테스트() {
        List<Long> productIds = List.of(1L, 2L);

        List<OrderProductsRequest> orderProductsRequests = List.of(
                new OrderProductsRequest(1L, 2),
                new OrderProductsRequest(2L, 3)
        );

        OrderRequest orderRequest = new OrderRequest(1L, orderProductsRequests, 10000L);
        Long order = orderUseCase.order(orderRequest);

        assertEquals(1L, order);
    }
}