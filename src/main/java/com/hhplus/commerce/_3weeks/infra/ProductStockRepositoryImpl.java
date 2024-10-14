package com.hhplus.commerce._3weeks.infra;

import com.hhplus.commerce._3weeks.infra.entity.ProductStockEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.webjars.NotFoundException;

@Repository
@RequiredArgsConstructor
public class ProductStockRepositoryImpl implements ProductStockRepository{
    private final ProductStockJpaRepository productStockJpaRepository;

    @Override
    public ProductStockEntity save(ProductStockEntity productStockEntity) {
        return productStockJpaRepository.save(productStockEntity);
    }

    @Override
    public ProductStockEntity lockedforStockfindById(Long id){
        return productStockJpaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("해당 상품의 재고 정보가 존재하지 않습니다."));
    }
}
