package com.hhplus.commerce._3weeks.infra.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orderItem")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class OrderItemEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id")
    private Long order_id;

    @Column(name = "product_id")
    private Long product_id;

    @Column(nullable = false)
    private Long quantity;

    @Column(nullable = false)
    private Long order_item_price;
}
