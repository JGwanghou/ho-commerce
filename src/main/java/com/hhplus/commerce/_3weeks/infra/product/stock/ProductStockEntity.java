package com.hhplus.commerce._3weeks.infra.product.stock;

import com.hhplus.commerce._3weeks.api.dto.request.OrderProductsRequest;
import com.hhplus.commerce._3weeks.common.config.BaseEntity;
import com.hhplus.commerce._3weeks.common.exception.OutOfStockException;
import com.hhplus.commerce._3weeks.domain.product.Product;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "product_stock")
@Getter
public class ProductStockEntity extends BaseEntity {
    @Id
    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "stock", nullable = false)
    private int stock;

    public ProductStockEntity decreaseStock(OrderProductsRequest request) {
        if (this.stock < request.getProduct_quantity()) {
            throw new OutOfStockException(request.getProduct_id() + "번 상품의 재고가 부족합니다.");
        }

        this.stock -= request.getProduct_quantity();
        return this;
    }
}
