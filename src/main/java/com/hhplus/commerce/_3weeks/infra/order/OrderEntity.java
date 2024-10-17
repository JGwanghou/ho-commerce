package com.hhplus.commerce._3weeks.infra.order;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table (name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long user_id;

    public OrderEntity(Long userId) {
        this.user_id = userId;
    }
}
