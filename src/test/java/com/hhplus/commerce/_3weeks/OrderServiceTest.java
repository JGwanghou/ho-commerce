package com.hhplus.commerce._3weeks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderServiceTest {

    private OrderService orderService;
    private OrderRepository orderRepository;

    private User user = new User(1, 5000);

    @BeforeEach
    public void setUp() {
        orderService = new OrderService();
        orderRepository = new OrderRepository();
        
    }

    @Test
    public void 주문하기() {
        Product buyProduct1 = new Product(98, 5);

        OrdersRequest request = new OrdersRequest(user.getId(), (List<Product>) buyProduct1);
//        orderService.order()
    }

    public class OrderService {

    }

    public class OrderRepository {
        private Long index = 0L;
        private Map<Long, OrdersRequest> map = new HashMap<>();

        public void save(OrdersRequest ordersRequest) {
            map.put(++index, ordersRequest);
        }
    }

    public class OrdersRequest {
        private int id;
        private int user_id;
        private List<Product> products;

        public  OrdersRequest(Integer user_id, List<Product> products) {
            this.user_id = user_id;
            this.products = products;
        }
    }




}
