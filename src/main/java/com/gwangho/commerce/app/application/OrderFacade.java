package com.gwangho.commerce.app.application;

import com.gwangho.commerce.app.api.order.OrderCreateItem;
import com.gwangho.commerce.app.api.order.OrderRequest;
import com.gwangho.commerce.app.api.order.OrderResponse;
import com.gwangho.commerce.app.domain.order.service.OrderCommand;
import com.gwangho.commerce.app.domain.order.service.OrderService;
import com.gwangho.commerce.app.domain.product.Product;
import com.gwangho.commerce.app.domain.product.service.ProductService;
import com.gwangho.commerce.app.domain.user.User;
import com.gwangho.commerce.app.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderFacade {
    private final UserService userService;
    private final OrderService orderService;
    private final ProductService productService;

    public OrderResponse createOrder(OrderCommand.CreateOrder command) {
        userService.findByIdOruUserNotFoundThrow(command.userId());
        List<Product> products = orderProductsInfo(command);

        return orderService.order(command, products);
    }

    private List<Product> orderProductsInfo(OrderCommand.CreateOrder command) {
        List<Long> productIds = command.createOrderItems()
                .stream()
                .map(OrderCommand.CreateOrderItem::productId)
                .toList();

        return productService.findProductsByIds(productIds);
    }
}
