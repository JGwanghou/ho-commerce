package com.hhplus.commerce._3weeks.infra.product;

import com.hhplus.commerce._3weeks.api.dto.request.OrderProductsRequest;
import com.hhplus.commerce._3weeks.domain.product.Product;
import com.hhplus.commerce._3weeks.domain.product.ProductRepository;
import com.hhplus.commerce._3weeks.domain.product.ProductStockRepository;
import com.hhplus.commerce._3weeks.infra.product.stock.ProductStockEntity;
import jakarta.persistence.EntityNotFoundException;
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

    @Transactional
    public List<ProductStockEntity> updateStock(List<OrderProductsRequest> req) {
        List<ProductStockEntity> stockEntities = new ArrayList<>();

        for (OrderProductsRequest orderRequest : req) {
            ProductStockEntity productStock = productStockRepository.findById(orderRequest.getProduct_id());
            productStock.decreaseStock(orderRequest.getProduct_quantity());

            stockEntities.add(productStock);
            productStockRepository.save(productStock);
        }

        return stockEntities;
    }
}