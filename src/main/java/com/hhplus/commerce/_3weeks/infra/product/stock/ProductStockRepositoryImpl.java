package com.hhplus.commerce._3weeks.infra.product.stock;

import com.hhplus.commerce._3weeks.api.dto.request.OrderProductsRequest;
import com.hhplus.commerce._3weeks.common.exception.ProductNotFoundException;
import com.hhplus.commerce._3weeks.domain.product.ProductStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductStockRepositoryImpl implements ProductStockRepository {
    private final ProductStockJpaRepository productStockJpaRepository;


    @Override
    public ProductStockEntity findById(Long id) {
        return productStockJpaRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("상품 재고 정보가 없습니다."));
    }

    @Override
    public ProductStockEntity save(ProductStockEntity productStock) {
        return productStockJpaRepository.save(productStock);
    }

    @Override
    public void decreaseStock(List<OrderProductsRequest> orderProductsRequests) {

    }
}
