package com.hhplus.commerce._3weeks.application;

import com.hhplus.commerce._3weeks.api.dto.request.OrderProductsRequest;
import com.hhplus.commerce._3weeks.api.dto.request.OrderRequest;
import com.hhplus.commerce._3weeks.domain.order.Order;
import com.hhplus.commerce._3weeks.domain.order.OrderService;
import com.hhplus.commerce._3weeks.domain.product.Product;
import com.hhplus.commerce._3weeks.domain.product.ProductService;
import com.hhplus.commerce._3weeks.domain.user.User;
import com.hhplus.commerce._3weeks.domain.user.UserService;
import com.hhplus.commerce._3weeks.infra.order.OrderEntity;
import com.hhplus.commerce._3weeks.infra.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderUseCase {
    private final UserService userService;
    private final ProductService productService;
    private final OrderService orderService;

    public Long order(OrderRequest request) {
        UserEntity userInfo = userService.getUserInfo(request.getUser_id());

        List<Product> products = productService.readProductByIds(
                request.getProducts().stream()
                .map(OrderProductsRequest::getProduct_id)
                .collect(Collectors.toList())
        );

        OrderEntity order = orderService.serviceOrder(request.getUser_id(), products, request);

        productService.decreaseStock(request.getProducts());

        userService.payment(userInfo, request);

        return order.getId();
    }
}
