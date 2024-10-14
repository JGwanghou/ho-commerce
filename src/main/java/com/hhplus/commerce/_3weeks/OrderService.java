package com.hhplus.commerce._3weeks;

import com.hhplus.commerce._3weeks.api.request.OrdersRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ProductService productService;

    private final UserService userService;

    public void startOrder(OrdersRequest orders) {
        int totalPrice = productService.calcTotalPrice(orders.getProducts());
        userService.validateUserPoints(orders.getUser_id(), totalPrice);



    }




}
