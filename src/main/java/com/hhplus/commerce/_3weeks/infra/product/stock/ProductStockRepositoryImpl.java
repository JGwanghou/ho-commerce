package com.hhplus.commerce._3weeks.infra.product.stock;

import com.hhplus.commerce._3weeks.api.dto.request.OrderProductsRequest;
import com.hhplus.commerce._3weeks.common.exception.ProductNotFoundException;
import com.hhplus.commerce._3weeks.domain.product.ProductStockRepository;
import jakarta.persistence.LockModeType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductStockRepositoryImpl implements ProductStockRepository {
    private final ProductStockJpaRepository productStockJpaRepository;


    @Override
    @Transactional(readOnly = true)
    public ProductStockEntity findById(Long id) {
        return productStockJpaRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public ProductStockEntity lockedfindById(Long id) {
        return productStockJpaRepository.findByProductIdWithPessimisticLock(id);
    }

    @Override
    public ProductStockEntity stockSave(ProductStockEntity productStock) {
        return productStockJpaRepository.save(productStock);
    }

    @Override
    public void decreaseStock(List<OrderProductsRequest> orderProductsRequests) {

    }
}
