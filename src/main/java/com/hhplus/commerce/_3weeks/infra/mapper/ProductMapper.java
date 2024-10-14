package com.hhplus.commerce._3weeks.infra.mapper;

import com.hhplus.commerce._3weeks.Product;
import com.hhplus.commerce._3weeks.infra.entity.ProductEntity;

public class ProductMapper {
    public static Product toDto(ProductEntity productEntity) {
        return new Product(productEntity.getName(), productEntity.getPrice());
    }

    public static ProductEntity toEntity(Product product) {
        return new ProductEntity(product.getId(), product.getName(), product.getPrice());
    }
}
