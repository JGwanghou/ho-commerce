package com.hhplus.commerce._3weeks.infra.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "productStock")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class ProductStockEntity extends BaseEntity{

    @Id
    private Long product_id;

    @Column(nullable = false)
    private Long stock;
}
