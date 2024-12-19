package com.gwangho.commerce.app.domain.order.infrastructure;

import com.gwangho.commerce.app.domain.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<Order, Long> {

}
