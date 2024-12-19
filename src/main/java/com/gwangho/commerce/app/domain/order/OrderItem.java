package com.gwangho.commerce.app.domain.order;

import com.gwangho.commerce.app.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "orders_item")
public class OrderItem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;
    private Long productId;

    private Long count; // 상품의 수량
    private BigDecimal orderPrice; // 주문가격

    @Builder
    public OrderItem(Long orderId, Long productId, Long count, BigDecimal orderPrice) {
        this.orderId = orderId;
        this.productId = productId;
        this.count = count;
        this.orderPrice = orderPrice;
    }
}
