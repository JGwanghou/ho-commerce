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
import com.hhplus.commerce._3weeks.infra.user.UserJpaRepository;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderUseCaseTest {

    @Autowired
    private OrderUseCase orderUseCase;

    @Autowired
    private UserJpaRepository userJpaRepository;

    @PostConstruct
    void setUp() {
        userJpaRepository.save(UserEntity.builder()
                .name("조말랑")
                .point(100_000_000L)
                .build());
    }

    @Test
    void 주문성공_테스트() {
        List<OrderProductsRequest> orderProductsRequests = List.of(
                new OrderProductsRequest(1L, 2)
        );

        OrderRequest orderRequest = new OrderRequest(1L, orderProductsRequests, 0L);
        Long order = orderUseCase.order(orderRequest);

        assertEquals(1L, order);
    }
}