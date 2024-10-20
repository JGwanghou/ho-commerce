package com.hhplus.commerce._3weeks.domain.product;

import java.util.List;

public interface ProductRepository {
    List<Product> findProductAll();
    List<Product> findByIdsWithStock(List<Long> productIds);
    Product findProductById(Long id);
}
