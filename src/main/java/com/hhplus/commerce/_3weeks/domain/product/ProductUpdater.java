package com.hhplus.commerce._3weeks.domain.product;

import com.hhplus.commerce._3weeks.api.dto.request.OrderProductsRequest;
import com.hhplus.commerce._3weeks.infra.RedisLockRepository;
import com.hhplus.commerce._3weeks.infra.product.stock.ProductStockEntity;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class ProductUpdater {
    private final ProductStockRepository productStockRepository;

    @Transactional
    public List<ProductStockEntity> updateStock(List<OrderProductsRequest> req) {
        List<ProductStockEntity> stockEntities = new ArrayList<>();

        for (OrderProductsRequest orderRequest : req) {
            ProductStockEntity productStock = productStockRepository.lockedfindById(orderRequest.getProduct_id());
            productStock.decreaseStock(orderRequest.getProduct_id(), (long) orderRequest.getProduct_quantity());

            stockEntities.add(productStock);
            productStockRepository.stockSave(productStock);
        }

        return stockEntities;
    }
//
    @Transactional
    public List<ProductStockEntity> LettuceUpdateStock(Long productId, Long quantity) {
        List<ProductStockEntity> stockEntities = new ArrayList<>();

        ProductStockEntity productStock = productStockRepository.findById(productId);
        productStock.decreaseStock(productId, quantity);
        stockEntities.add(productStock);
        productStockRepository.stockSave(productStock);

        return stockEntities;
    }

    @Transactional
    public List<ProductStockEntity> RedissonUpdateStock(Long productId, Long quantity) {
        List<ProductStockEntity> stockEntities = new ArrayList<>();

        ProductStockEntity productStock = productStockRepository.findById(productId);
        productStock.decreaseStock(productId, quantity);
        stockEntities.add(productStock);
        productStockRepository.stockSave(productStock);

        return stockEntities;
    }
}