package com.hhplus.commerce._3weeks.domain.product;

import com.hhplus.commerce._3weeks.api.dto.request.OrderProductsRequest;
import com.hhplus.commerce._3weeks.infra.RedisLockRepository;
import com.hhplus.commerce._3weeks.infra.product.stock.ProductStockEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductUpdater {
    private final ProductRepository productRepository;
    private final ProductStockRepository productStockRepository;
    private final RedisLockRepository redisLockRepository;

    @Transactional
    public List<ProductStockEntity> updateStock(List<OrderProductsRequest> req) {
        List<ProductStockEntity> stockEntities = new ArrayList<>();

        for (OrderProductsRequest orderRequest : req) {
            ProductStockEntity productStock = productStockRepository.lockedfindById(orderRequest.getProduct_id());
            productStock.decreaseStock(orderRequest);

            stockEntities.add(productStock);
            productStockRepository.stockSave(productStock);
        }

        return stockEntities;
    }

    @Transactional
    public List<ProductStockEntity> LettuceUpdateStock(List<OrderProductsRequest> req) {
        List<ProductStockEntity> stockEntities = new ArrayList<>();

        int retryCount = 5;
        long retryDelayMs = 200;

        for (OrderProductsRequest orderRequest : req) {
            String lockKey = "lock:productId-" + orderRequest.getProduct_id();
            boolean lockAcquired = false;

            try {
                for (int i = 0; i < retryCount; i++) {
                    lockAcquired = redisLockRepository.lock(lockKey);
                    if(lockAcquired){
                        break;
                    }

                    Thread.sleep(retryDelayMs);
                }

                ProductStockEntity productStock = productStockRepository.findById(orderRequest.getProduct_id());
                productStock.decreaseStock(orderRequest);

                stockEntities.add(productStock);
                productStockRepository.stockSave(productStock);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                if (lockAcquired) {
                    redisLockRepository.unlock(lockKey);
                }
            }
        }

        return stockEntities;
    }
}