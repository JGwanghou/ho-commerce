package com.gwangho.commerce.app.domain.point;

import com.gwangho.commerce.app.common.BaseEntity;
import com.gwangho.commerce.app.domain.point.enums.PointType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Point extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @Enumerated(EnumType.STRING)
    private PointType type;

    private BigDecimal amount;

    private BigDecimal balanceAfter;

    @Builder
    public Point(Long id, Long userId, PointType status, BigDecimal amount, BigDecimal balanceAfter) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
    }

    public Point charge(Long userId, PointType type, BigDecimal amount){
        this.userId = userId;
        this.type = type;
        this.amount = amount;

        //
        if(Objects.equals(this.balanceAfter, BigDecimal.ZERO)) {
            this.balanceAfter = amount;
        }

        return this;
    }
}
