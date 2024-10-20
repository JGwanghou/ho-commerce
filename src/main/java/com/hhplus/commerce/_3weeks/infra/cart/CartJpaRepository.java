package com.hhplus.commerce._3weeks.infra.cart;

import com.hhplus.commerce._3weeks.domain.product.Product;
import com.hhplus.commerce._3weeks.infra.product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartJpaRepository extends JpaRepository<CartEntity, Long> {
    Optional<CartEntity> findByUserId(Long userId);
}
