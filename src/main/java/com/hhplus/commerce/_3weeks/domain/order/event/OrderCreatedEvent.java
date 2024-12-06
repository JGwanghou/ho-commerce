package com.hhplus.commerce._3weeks.domain.order.event;

import com.hhplus.commerce._3weeks.api.dto.request.OrderProductsRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.List;

@Getter
public class OrderCreatedEvent extends ApplicationEvent {
    private Long userId;
    private List<OrderProductsRequest> products;

    public OrderCreatedEvent(Object source, Long userId, List<OrderProductsRequest> products) {
        super(source);
        this.userId = userId;
        this.products = products;
    }
}
