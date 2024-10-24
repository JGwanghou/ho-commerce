package com.hhplus.commerce._3weeks.infra.cart;

import com.hhplus.commerce._3weeks.common.config.BaseEntity;
import com.hhplus.commerce._3weeks.domain.cart.Cart;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cart")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userId")
    private Long userId;

    public Long getId() {
        return id;
    }

    public CartEntity(Long userId) {
        this.userId = userId;
    }
    public Cart toCart() {
        return new Cart(getId(), userId);
    }
}
