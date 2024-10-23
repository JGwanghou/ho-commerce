package com.hhplus.commerce._3weeks.domain.product;

import com.hhplus.commerce._3weeks.api.dto.request.OrderProductsRequest;
import com.hhplus.commerce._3weeks.infra.product.ProductUpdater;
import com.hhplus.commerce._3weeks.infra.product.stock.ProductStockEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductReader productReader;
    private final ProductUpdater productUpdater;

    public List<Product> readProductAll() {
        return productReader.readProductAll();
    }

    public List<Product> readProductByIds(List<Long> productIds) {
        return productReader.readProductByIds(productIds);
    }

    public List<Product> readProductPopulars() {
        return productReader.readProductPopulars();
    }

    public List<ProductStockEntity> decreaseStock(List<OrderProductsRequest> products) {
         return productUpdater.updateStock(products);
    }

    public Product readProductDetail(Long productId) {
        return productReader.readProductDetail(productId);
    }
}
