package com.hhplus.commerce._3weeks.domain.order;

import com.hhplus.commerce._3weeks.infra.order.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Order {
    private Long id;
    private Long user_id;

    public static Order from(OrderEntity orderEntity) {
        return new Order(orderEntity.getId(), orderEntity.getUser_id());
    }
}
