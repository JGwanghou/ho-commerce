package com.hhplus.commerce._3weeks.infra;

import com.hhplus.commerce._3weeks.infra.entity.ProductStockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductStockJpaRepository extends JpaRepository<ProductStockEntity, Long> {
}
