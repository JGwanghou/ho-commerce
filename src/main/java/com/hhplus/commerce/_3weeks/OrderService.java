package com.hhplus.commerce._3weeks;

import com.hhplus.commerce._3weeks.api.request.OrdersRequest;
import com.hhplus.commerce._3weeks.exception.OutOfStockException;
import com.hhplus.commerce._3weeks.infra.entity.ProductStockEntity;
import com.hhplus.commerce._3weeks.infra.mapper.ProductStockMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ProductService productService;
    private final UserService userService;
    private final ProductStockService productStockService;

    @Transactional(rollbackFor = OutOfStockException.class)
    public void startOrder(OrdersRequest orders) {
        int totalPrice = productService.calcTotalPrice(orders.getProducts());

        userService.validateUserPoints(orders.getUser_id(), totalPrice);
        productStockService.validateStockAfterDecrease(orders);

        // 유저 잔고 감소
        // Order 저장
        // Order Item 저장

    }




}
