package com.gwangho.commerce.app.api.point;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
public class PointViewResponse {
    String idempotencyKey;
    Long userId;
    String name;
    String hpNo;
    BigDecimal chargeAmount;

    @Builder
    public PointViewResponse(String idempotencyKey, Long userId, String name, String hpNo, BigDecimal chargeAmount) {
        this.idempotencyKey = idempotencyKey;
        this.userId = userId;
        this.name = name;
        this.hpNo = hpNo;
        this.chargeAmount = chargeAmount;
    }
}
