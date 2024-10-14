package com.hhplus.commerce._3weeks;

import com.hhplus.commerce._3weeks.api.request.OrderProductsRequest;
import com.hhplus.commerce._3weeks.api.request.OrdersRequest;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    private User user;

    private List<OrderProductsRequest> productsRequests;
    private OrdersRequest orders;
    @BeforeEach
    public void setup() {
        productsRequests = List.of(
                new OrderProductsRequest(1L, 4),
                new OrderProductsRequest(2L, 2)
        );

        user = new User(5L, 5000);
        orders = new OrdersRequest(1L, user.getId(), productsRequests);
    }

    @Test
    public void 구매예정_상품_총_가격_계산() {
        int totalprice = productService.calcTotalPrice(orders.getProducts());

        assertEquals(18000, totalprice);
    }

    @Test
    public void 유저_잔고부족_예외(){
        int totalprice = productService.calcTotalPrice(orders.getProducts());

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            userService.validateUserPoints(orders.getUser_id(), totalprice);
        });

        assertEquals("잔고부족", exception.getMessage());
    }





    @Test
    public void 주문하기() {
        orderService.startOrder(orders);
    }



}
