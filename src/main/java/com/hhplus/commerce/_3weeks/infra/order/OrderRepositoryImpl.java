package com.hhplus.commerce._3weeks.infra.order;

import com.hhplus.commerce._3weeks.domain.order.Order;
import com.hhplus.commerce._3weeks.domain.order.OrderRepository;
import com.hhplus.commerce._3weeks.domain.product.Product;
import com.hhplus.commerce._3weeks.domain.product.ProductRepository;
import com.hhplus.commerce._3weeks.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {
    private final OrderJpaRepository orderJpaRepository;

    @Override
    public OrderEntity saveUserId(Long userId) {
        return orderJpaRepository.save(new OrderEntity(userId));
    }

}
