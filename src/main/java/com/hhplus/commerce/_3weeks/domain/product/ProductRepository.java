package com.hhplus.commerce._3weeks.domain.product;

import java.util.List;

public interface ProductRepository {
    List<Product> findProductAll();
    Product findProductById(Long id);
}
