package com.hhplus.commerce._3weeks.infra;

import com.hhplus.commerce._3weeks.infra.entity.ProductStockEntity;

import java.util.Optional;

public interface ProductStockRepository {
    ProductStockEntity save(ProductStockEntity productStockEntity);
    ProductStockEntity lockedforStockfindById(Long id);
}
