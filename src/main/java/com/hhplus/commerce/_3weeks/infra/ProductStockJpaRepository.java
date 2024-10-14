package com.hhplus.commerce._3weeks.infra;

import com.hhplus.commerce._3weeks.infra.entity.ProductStockEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface ProductStockJpaRepository extends JpaRepository<ProductStockEntity, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<ProductStockEntity> findById(Long id);
}
