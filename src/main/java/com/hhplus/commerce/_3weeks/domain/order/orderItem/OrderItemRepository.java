package com.hhplus.commerce._3weeks.domain.order.orderItem;


import com.hhplus.commerce._3weeks.infra.order.orderItem.OrderItemEntity;

import java.util.List;

public interface OrderItemRepository {
    List<OrderItemEntity> saveItems(List<OrderItemEntity> orderItems);
}
