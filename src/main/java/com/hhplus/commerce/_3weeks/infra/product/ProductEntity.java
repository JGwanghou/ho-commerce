package com.hhplus.commerce._3weeks.infra.product;

import com.hhplus.commerce._3weeks.common.config.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Table(name = "product")
@Builder
public class ProductEntity extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "price", nullable = false)
    private int price;
}
