package com.hhplus.commerce._3weeks.infra;

import com.hhplus.commerce._3weeks.Product;
import com.hhplus.commerce._3weeks.exception.ProductNotFoundException;
import com.hhplus.commerce._3weeks.infra.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository{

    private final ProductJpaRepository productJpaRepository;

    @Override
    public ProductEntity getProduct(Long id) {
        return productJpaRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("상품이 존재하지 않습니다."));
    }
}
