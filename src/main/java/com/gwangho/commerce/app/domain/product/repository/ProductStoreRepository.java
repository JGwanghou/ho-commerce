package com.gwangho.commerce.app.domain.product.repository;

import com.gwangho.commerce.app.domain.product.Product;

public interface ProductStoreRepository {
    Product save(Product entity);
}
