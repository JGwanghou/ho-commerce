package com.hhplus.commerce._3weeks.domain.product;

import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductRepository {
    List<Product> findProductAll();
    List<Product> findByIdsWithStock(List<Long> productIds);
    List<Product> findProductPopulars(LocalDateTime startDate,
                                      LocalDateTime endDate,
                                      Pageable pageable);
    Product findProductById(Long id);
}
