package com.hhplus.commerce._3weeks.infra.product.stock;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductStockJpaRepository extends JpaRepository<ProductStockEntity, Long> {


    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select ps from ProductStockEntity ps where ps.productId = :productId")
    ProductStockEntity findByProductIdWithPessimisticLock(@Param("productId") Long productId);
}
