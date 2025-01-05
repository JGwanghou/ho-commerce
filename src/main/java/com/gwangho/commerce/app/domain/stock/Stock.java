package com.gwangho.commerce.app.domain.stock;

import com.gwangho.commerce.app.common.BaseEntity;
import com.gwangho.commerce.app.common.exception.OutOfStockException;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "stock")
public class Stock extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;

    private Long quantity;

    @Builder
    public Stock(Long productId, Long quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Stock decrease(Long quantity) {
        validDecrease(quantity);

        this.quantity -= quantity;
        return this;
    }

    public boolean validDecrease(Long quantity){
        if (this.quantity < quantity) {
            throw new OutOfStockException();
        }

        return true;
    }
}
