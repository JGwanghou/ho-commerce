package com.hhplus.commerce._3weeks.domain.order;

import com.hhplus.commerce._3weeks.api.dto.request.OrderProductsRequest;
import com.hhplus.commerce._3weeks.domain.order.orderItem.OrderItemRepository;
import com.hhplus.commerce._3weeks.domain.product.Product;
import com.hhplus.commerce._3weeks.infra.order.OrderEntity;
import com.hhplus.commerce._3weeks.infra.order.orderItem.OrderItemEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderItemAppender {
    private final OrderItemRepository orderItemRepository;

    public List<OrderItemEntity> create(OrderEntity order, List<Product> products, List<OrderProductsRequest> orderItems){
        List<OrderItemEntity> orderItemEntities = new ArrayList<>();

        for (OrderProductsRequest prod : orderItems) {
            orderItemEntities.add(
                    OrderItemEntity.builder()
                            .order_id(order.getId())
                            .product_id(prod.getProduct_id())
                            .quantity(prod.getProduct_quantity())
                            .build()
            );
        }

        return orderItemRepository.saveItems(orderItemEntities);
    }
}
