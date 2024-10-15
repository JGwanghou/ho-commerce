package com.hhplus.commerce._3weeks.infra.product;

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
public class ProductStockEntity {
    @Id
    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "stock", nullable = false)
    private int stock;
}
