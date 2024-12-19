package com.gwangho.commerce.app.domain.order;

import com.gwangho.commerce.app.common.BaseEntity;
import com.gwangho.commerce.app.domain.order.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private BigDecimal totalPrice;

    @Builder
    public Order(Long userId, OrderStatus status, BigDecimal totalPrice) {
        this.userId = userId;
        this.status = status;
        this.totalPrice = totalPrice;
    }
}
