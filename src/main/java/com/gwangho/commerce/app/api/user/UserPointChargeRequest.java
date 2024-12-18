package com.gwangho.commerce.app.api.user;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class UserPointChargeRequest {

    private Long userId;
    private String name;
    private String hpNo;

    @DecimalMin(value = "1000", message = "최소 충전 금액은 1,000원입니다.")
    @DecimalMax(value = "1000000", message = "최대 충전 금액은 1,000,000원입니다.")
    private BigDecimal chargeAmount;

    public UserPointChargeRequest(Long userId, BigDecimal chargeAmount) {
        this.chargeAmount = chargeAmount;
    }
}
