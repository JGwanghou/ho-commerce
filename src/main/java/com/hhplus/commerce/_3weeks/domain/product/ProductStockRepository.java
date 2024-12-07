package com.hhplus.commerce._3weeks.domain.product;

import com.hhplus.commerce._3weeks.api.dto.request.OrderProductsRequest;
import com.hhplus.commerce._3weeks.infra.product.stock.ProductStockEntity;

import java.util.List;

public interface ProductStockRepository {
    ProductStockEntity findById(Long id);
    ProductStockEntity findByProductIdWithPessimisticLock(Long id);
    ProductStockEntity stockSave(ProductStockEntity productStock);
    void decreaseStock(List<OrderProductsRequest> orderProductsRequests);
}
