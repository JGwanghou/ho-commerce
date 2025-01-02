package com.gwangho.commerce.app.domain.payment;

import com.gwangho.commerce.app.domain.payment.enums.PaymentType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long order_id;
    private BigDecimal totalPrice = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    private PaymentType payMethod;

    private String status;

    @Builder
    public Payment(Long order_id, BigDecimal totalPrice, PaymentType payMethod) {
        this.order_id = order_id;
        this.totalPrice = totalPrice;
        this.payMethod = payMethod;
        this.status = order_id == null ? "충전" : "사용";
    }
}
