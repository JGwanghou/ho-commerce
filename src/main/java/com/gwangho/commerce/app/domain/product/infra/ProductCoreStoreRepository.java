package com.gwangho.commerce.app.domain.product.infra;

import com.gwangho.commerce.app.common.exception.ProductNotFoundException;
import com.gwangho.commerce.app.domain.product.Product;
import com.gwangho.commerce.app.domain.product.repository.ProductJpaRepository;
import com.gwangho.commerce.app.domain.product.repository.ProductReaderRepository;
import com.gwangho.commerce.app.domain.product.repository.ProductStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductCoreStoreRepository implements ProductStoreRepository {
    private final ProductJpaRepository productJpaRepository;

    @Override
    public Product save(Product entity) {
        return productJpaRepository.save(entity);
    }
}
