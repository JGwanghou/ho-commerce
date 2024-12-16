package com.gwangho.commerce.app.api.point;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
public class PointResponse {
    Long userId;
    String name;
    String hpNo;
    BigDecimal chargeAmount;

    @Builder
    public PointResponse(Long userId, String name, String hpNo, BigDecimal chargeAmount) {
        this.userId = userId;
        this.name = name;
        this.hpNo = hpNo;
        this.chargeAmount = chargeAmount;
    }
}
