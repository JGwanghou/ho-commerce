package com.gwangho.commerce.app.domain.product.repository;

import com.gwangho.commerce.app.domain.product.Product;

import java.util.List;

public interface ProductReaderRepository {
    List<Product> findProductsByIds(List<Long> productIds);
    Product findByIdOrProductNotFoundThrow(Long productId);
}
