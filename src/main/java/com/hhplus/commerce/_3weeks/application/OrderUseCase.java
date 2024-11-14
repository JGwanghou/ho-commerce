package com.hhplus.commerce._3weeks.application;

import com.hhplus.commerce._3weeks.api.dto.request.OrderProductsRequest;
import com.hhplus.commerce._3weeks.api.dto.request.OrderRequest;
import com.hhplus.commerce._3weeks.domain.order.OrderService;
import com.hhplus.commerce._3weeks.domain.order.event.OrderCreatedEvent;
import com.hhplus.commerce._3weeks.domain.product.Product;
import com.hhplus.commerce._3weeks.domain.product.ProductService;
import com.hhplus.commerce._3weeks.domain.user.UserService;
import com.hhplus.commerce._3weeks.infra.order.OrderEntity;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderUseCase {
    private final UserService userService;
    private final ProductService productService;
    private final OrderService orderService;

    private final RedissonClient redissonClient;
    private final ApplicationEventPublisher applicationEventPublisher;

    public Long order(OrderRequest request) {

        request.setPaymentPrice((long) getProductsPrice(request));

        // 1. Order테이블, OrderItem 테이블 저장
        OrderEntity order = orderService.serviceOrder(request.getUser_id(), request);

        // 2. 재고 감소
        for (OrderProductsRequest prod : request.getProducts()) {
            RLock rLock = redissonClient.getLock(String.format("LOCK:PROD-%d", prod.getProduct_id()));

            try {
                boolean available = rLock.tryLock(5, 1, TimeUnit.SECONDS);
                if(!available) {
                    throw new IllegalArgumentException("Lock Not acquired");
                }
                productService.RedissonDecreaseStock(prod.getProduct_id(), (long) prod.getProduct_quantity());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                rLock.unlock();
            }
        }

        // 3. 유저 포인트 차감
        userService.payment(request.getUser_id(), request.getPaymentPrice());
        applicationEventPublisher.publishEvent(new OrderCreatedEvent(this, request.getUser_id(), request.getProducts()));
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
