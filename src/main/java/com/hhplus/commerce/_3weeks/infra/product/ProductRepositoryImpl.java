package com.hhplus.commerce._3weeks.infra.product;

import com.hhplus.commerce._3weeks.domain.product.Product;
import com.hhplus.commerce._3weeks.domain.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {
    private final ProductJpaRepository productJpaRepository;

    @Override
    public List<Product> findProductAll() {
        return productJpaRepository.findAllWithStock();
    }

    @Override
    public List<Product> findByIdsWithStock(List<Long> productIds) {
        return productJpaRepository.findByIdsWithStock(productIds);
    }

    @Override
    public List<Product> findProductPopulars(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable) {
        return productJpaRepository.findProductPopulars(startDate, endDate, pageable);
    }

    @Override
    public Product findProductById(Long id) {
        return productJpaRepository.findByIdWithStock(id);
    }
}
