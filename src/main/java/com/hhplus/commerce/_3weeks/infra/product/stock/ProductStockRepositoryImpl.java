package com.hhplus.commerce._3weeks.infra.product.stock;

import com.hhplus.commerce._3weeks.domain.product.ProductStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductStockRepositoryImpl implements ProductStockRepository {
    private final ProductStockJpaRepository productStockJpaRepository;


}
