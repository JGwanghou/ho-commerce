package com.hhplus.commerce._3weeks.infra.order.orderItem;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "orderItem")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class OrderItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id")
    private Long order_id;

    @Column(name = "product_id")
    private Long product_id;

    @Column(nullable = false)
    private int quantity;

    @Builder
    public OrderItemEntity(Long order_id, Long product_id, int quantity) {
        this.order_id = order_id;
        this.product_id = product_id;
        this.quantity = quantity;
    }
}
