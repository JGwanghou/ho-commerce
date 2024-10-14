package com.hhplus.commerce._3weeks.infra.mapper;

import com.hhplus.commerce._3weeks.Product;
import com.hhplus.commerce._3weeks.ProductStock;
import com.hhplus.commerce._3weeks.infra.entity.ProductEntity;
import com.hhplus.commerce._3weeks.infra.entity.ProductStockEntity;

public class ProductStockMapper {
    public static ProductStock toDto(ProductStockEntity productStockEntity) {
        return new ProductStock(productStockEntity.getProduct_id(), productStockEntity.getStock());
    }

    public static ProductStockEntity toEntity(ProductStock productStock) {
        return new ProductStockEntity(productStock.getId(), productStock.getStock());
    }
}
