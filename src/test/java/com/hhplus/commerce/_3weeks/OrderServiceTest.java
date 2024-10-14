package com.hhplus.commerce._3weeks;

import com.hhplus.commerce._3weeks.api.request.OrderProductsRequest;
import com.hhplus.commerce._3weeks.api.request.OrdersRequest;
import static org.junit.jupiter.api.Assertions.*;

import com.hhplus.commerce._3weeks.exception.OutOfStockException;
import com.hhplus.commerce._3weeks.infra.entity.ProductStockEntity;
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

    @Autowired
    private ProductStockService productStockService;

    private User user;

    private OrdersRequest orders;
    private List<OrderProductsRequest> productsRequests;

    @BeforeEach
    public void setup() {
        productsRequests = List.of(
                new OrderProductsRequest(1L, 4L),
                new OrderProductsRequest(2L, 2L)
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
    public void 재고_감소_예외() {
        List<OrderProductsRequest> productsRequestsThrow = List.of(
                new OrderProductsRequest(1L, 11L)
        );

        User throwStockUser = new User(5L, 50000);
        OrdersRequest throwStockOrders = new OrdersRequest(1L, throwStockUser.getId(), productsRequestsThrow);

        assertThrows(OutOfStockException.class, () -> {
            productStockService.validateStockAfterDecrease(throwStockOrders);
        });
    }

    @Test
    public void 재고_감소_정상처리() {
        List<OrderProductsRequest> productsRequestsOk = List.of(
                new OrderProductsRequest(1L, 9L),
                new OrderProductsRequest(2L, 20L)
        );

        User userOk = new User(5L, 50000);
        OrdersRequest ordersOk = new OrdersRequest(1L, userOk.getId(), productsRequestsOk);
        productStockService.validateStockAfterDecrease(ordersOk);

        assertEquals(1, productStockService.lockedforStockfindById(1L).getStock());
    }

    @Test
    public void 재고_감소_롤백() {
        List<OrderProductsRequest> productsRequestsRollback = List.of(
                new OrderProductsRequest(1L, 9L),
                new OrderProductsRequest(2L, 21L)
        );

//        User rollbackUser = new User(5L, 50000);
//        OrdersRequest rollbackOrders = new OrdersRequest(1L, userOk.getId(), productsRequestsOk);
//        productStockService.validateStockAfterDecrease(ordersOk);

        assertEquals(1, productStockService.lockedforStockfindById(1L).getStock());
    }


    @Test
    public void 주문하기() {
        orderService.startOrder(orders);
    }



}
