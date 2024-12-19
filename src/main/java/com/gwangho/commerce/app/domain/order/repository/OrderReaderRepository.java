package com.gwangho.commerce.app.domain.order.repository;

import com.gwangho.commerce.app.domain.order.Order;
import org.springframework.stereotype.Repository;


public interface OrderReaderRepository {
    Order findByIdOrOrderNotFoundThrow(Long orderId);
}
