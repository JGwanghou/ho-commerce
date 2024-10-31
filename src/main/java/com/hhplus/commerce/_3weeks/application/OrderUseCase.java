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

        request.setPaymentPrice((long) getProductsPrice(request));

        // 1. Order테이블, OrderItem 테이블 저장
        OrderEntity order = orderService.serviceOrder(request.getUser_id(), request);

        // 2. 재고 감소
        productService.decreaseStock(request.getProducts());

        // 3. 유저 포인트 차감
        userService.payment(request.getUser_id(), request.getPaymentPrice());

        return order.getId();
    }

    public Long orderWithLettuce(OrderRequest request) {

        request.setPaymentPrice((long) getProductsPrice(request));

        // 1. Order테이블, OrderItem 테이블 저장
        OrderEntity order = orderService.serviceOrder(request.getUser_id(), request);

        // 2. 재고 감소
        productService.LettuceDecreaseStock(request.getProducts());

        // 3. 유저 포인트 차감
        userService.payment(request.getUser_id(), request.getPaymentPrice());

        return order.getId();
    }

    public Long orderWithRedisson(OrderRequest request) {

        request.setPaymentPrice((long) getProductsPrice(request));

        // 1. Order테이블, OrderItem 테이블 저장
        OrderEntity order = orderService.serviceOrder(request.getUser_id(), request);

        // 2. 재고 감소
        productService.RedissonDecreaseStock(request.getProducts());

        // 3. 유저 포인트 차감
        userService.payment(request.getUser_id(), request.getPaymentPrice());

        return order.getId();
    }


    private Integer getProductsPrice(OrderRequest request) {
        List<Product> products = productService.readProductByIds(
                request.getProducts().stream()
                .map(OrderProductsRequest::getProduct_id)
                .collect(Collectors.toList())
        );

        return products.stream()
                .flatMap(product -> request.getProducts().stream()
                        .filter(orderProduct -> orderProduct.getProduct_id().equals(product.getId()))
                        .map(orderProduct -> product.getPrice() * orderProduct.getProduct_quantity()))
                .reduce(0, Integer::sum);
    }

}
