package com.gwangho.commerce.app.domain.order.infrastructure;

import com.gwangho.commerce.app.domain.order.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemJpaRepository extends JpaRepository<OrderItem, Long> {

}
