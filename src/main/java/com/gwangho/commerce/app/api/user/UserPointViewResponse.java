package com.gwangho.commerce.app.api.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.text.DecimalFormat;

@NoArgsConstructor
@Getter
public class UserPointViewResponse {
    String idempotencyKey;
    Long userId;
    String name;
    String hpNo;
    String chargeAmount;

    @Builder
    public UserPointViewResponse(String idempotencyKey, Long userId, String name, String hpNo, BigDecimal chargeAmount) {
        this.idempotencyKey = idempotencyKey;
        this.userId = userId;
        this.name = name;
        this.hpNo = hpNo;
        this.chargeAmount = new DecimalFormat("#,###").format(chargeAmount);
    }
}
