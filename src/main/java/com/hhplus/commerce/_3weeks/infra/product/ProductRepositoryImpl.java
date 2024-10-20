package com.hhplus.commerce._3weeks.infra.product;

import com.hhplus.commerce._3weeks.domain.product.Product;
import com.hhplus.commerce._3weeks.domain.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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
        return List.of();
    }

    @Override
    public Product findProductById(Long id) {
        return productJpaRepository.findByIdWithStock(id);
    }
}
