package com.gwangho.commerce.app.api.point;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class PointChargeRequest {

    @Positive(message = "충전 금액은 0원 이상이어야 합니다.")
    private BigDecimal chargeAmount;

    public PointChargeRequest(Long userId, BigDecimal chargeAmount) {
        this.chargeAmount = chargeAmount;
    }
}
