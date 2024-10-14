package com.hhplus.commerce._3weeks;

import com.hhplus.commerce._3weeks.api.request.OrderProductsRequest;
import com.hhplus.commerce._3weeks.api.request.OrdersRequest;
import com.hhplus.commerce._3weeks.exception.OutOfStockException;
import com.hhplus.commerce._3weeks.infra.ProductRepository;
import com.hhplus.commerce._3weeks.infra.ProductStockRepository;
import com.hhplus.commerce._3weeks.infra.entity.ProductStockEntity;
import com.hhplus.commerce._3weeks.infra.mapper.ProductMapper;
import com.hhplus.commerce._3weeks.infra.mapper.ProductStockMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductStockService {
    private final ProductStockRepository productStockRepository;

    public ProductStock save(ProductStockEntity productStockEntity) {
        return ProductStockMapper.toDto(productStockRepository.save(productStockEntity));
    }

    public ProductStock lockedforStockfindById(Long id) {
        return ProductStockMapper.toDto(productStockRepository.lockedforStockfindById(id));
    }

    public void validateStockAfterDecrease(OrdersRequest orders) {
        orders.getProducts().forEach(products -> {
            ProductStock productStock = ProductStockMapper.toDto(productStockRepository.lockedforStockfindById(products.getProduct_id()));
            if(productStock.getStock() < products.getBuy_count()){
                throw new OutOfStockException("상품번호 : " + products.getProduct_id() + "의 상품재고가 부족합니다.");
            }

            productStockRepository.save(ProductStockMapper.toEntity(productStock.decreaseStock(products.getBuy_count())));
        });
    }
}
