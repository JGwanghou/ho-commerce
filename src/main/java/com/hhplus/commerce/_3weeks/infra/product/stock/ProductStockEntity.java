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
import lombok.extern.slf4j.Slf4j;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "product_stock")
@Getter
@Slf4j
public class ProductStockEntity extends BaseEntity {
    @Id
    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "stock", nullable = false)
    private int stock;

    public ProductStockEntity decreaseStock(Long productId, Long quantity) {
        if (this.stock < quantity) {
            throw new OutOfStockException();
        }

        this.stock -= quantity;
        return this;
    }
}
