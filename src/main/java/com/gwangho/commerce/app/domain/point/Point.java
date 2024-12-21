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
@Table(name = "point")
public class Point extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @Enumerated(EnumType.STRING)
    private PointType type;

    @Column(precision = 10, scale = 1, nullable = false)
    private BigDecimal amount;

    @Builder
    public Point(Long id, Long userId, PointType type, BigDecimal amount) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.amount = amount;
    }
}
