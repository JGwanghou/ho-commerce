package com.gwangho.commerce.app.domain.user;

import com.gwangho.commerce.app.common.BaseEntity;
import com.gwangho.commerce.app.common.exception.InsufficientBalanceException;
import com.gwangho.commerce.app.common.exception.InvalidChargeAmountException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(precision = 7, scale = 1, nullable = false)
    private BigDecimal point = BigDecimal.ZERO;

    private String hpNo;

    @Builder
    public User(Long id, String name, String hpNo) {
        this.id = id;
        this.name = name;
        this.hpNo = hpNo;
    }

    public User addPoint(BigDecimal amount){
        if (
                amount.compareTo(BigDecimal.valueOf(1000)) < 0 ||
                amount.compareTo(BigDecimal.valueOf(1_000_000)) > 0 ||
                amount == null ||
                amount.compareTo(BigDecimal.ZERO) <= 0)
        {
            throw new InvalidChargeAmountException();
        }

        this.point = this.point.add(amount);
        return this;
    }

    public User decreasePoint(BigDecimal amount){
        if (this.point.compareTo(amount) < 0)
        {
            throw new InsufficientBalanceException();
        }

        if (amount.compareTo(BigDecimal.ZERO) <= 0){
            throw new InvalidChargeAmountException();
        }

        this.point = this.point.subtract(amount);
        return this;
    }
}
