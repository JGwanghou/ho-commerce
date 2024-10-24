package com.hhplus.commerce._3weeks.infra.cart.cartitem;

import com.hhplus.commerce._3weeks.common.config.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cart_item")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartItemEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cart_id")
    private Long cartId;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "quantity")
    private Long quantity;

    public CartItemEntity(Long cartId, Long productId, Long quantity) {
        this.id = null;
        this.cartId = cartId;
        this.productId = productId;
        this.quantity = quantity;
    }
}
