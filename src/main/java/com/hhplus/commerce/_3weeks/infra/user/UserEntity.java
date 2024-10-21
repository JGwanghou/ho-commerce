package com.hhplus.commerce._3weeks.infra.user;

import com.hhplus.commerce._3weeks.common.exception.InsufficientBalanceException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "point")
    private Long point;

    @Builder
    public UserEntity(Long id, String name, Long point) {
        this.id = id;
        this.name = name;
        this.point = point;
    }

    public void chargePoint(Long paymentPrice) {
        if(paymentPrice <= 0){
            throw new IllegalArgumentException("충전 금액을 다시 확인해주세요. [충전요청 : " + paymentPrice + "]");
        }

        this.point += paymentPrice;
    }

    public void validPoint(Long paymentPrice) {
        if (this.point < paymentPrice) {
            throw new InsufficientBalanceException("잔고가 부족합니다.");
        }

        this.point -= paymentPrice;
    }
}
