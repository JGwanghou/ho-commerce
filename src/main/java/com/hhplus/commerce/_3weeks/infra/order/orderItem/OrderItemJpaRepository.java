package com.hhplus.commerce._3weeks.infra.order.orderItem;

import com.hhplus.commerce._3weeks.infra.order.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemJpaRepository extends JpaRepository<OrderItemEntity, Long> {

}
