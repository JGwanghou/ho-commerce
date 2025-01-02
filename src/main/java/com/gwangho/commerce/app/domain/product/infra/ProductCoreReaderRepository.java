package com.gwangho.commerce.app.domain.product.infra;

import com.gwangho.commerce.app.common.exception.ProductNotFoundException;
import com.gwangho.commerce.app.domain.product.Product;
import com.gwangho.commerce.app.domain.product.repository.ProductJpaRepository;
import com.gwangho.commerce.app.domain.product.repository.ProductReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductCoreReaderRepository implements ProductReaderRepository {
    private final ProductJpaRepository productJpaRepository;

    @Override
    public List<Product> findProductsByIds(List<Long> productIds) {
        return productJpaRepository.findAllById(productIds);
    }

    @Override
    public Product findByIdOrProductNotFoundThrow(Long productId) {
        return productJpaRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
    }
}
