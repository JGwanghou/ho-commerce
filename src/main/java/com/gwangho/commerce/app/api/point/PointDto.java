package com.gwangho.commerce.app.api.point;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.math.BigDecimal;

public class PointDto {
    public record PointCreate(
            @NotNull
            Long userId,

            @Positive(message = "충전 금액은 0원 이상이어야 합니다.")
            @NotNull
            BigDecimal chargeAmount
    ){

    }

    @Builder
    public record PointResponse(
            Long userId,
            String name,
            String hpNo,
            BigDecimal chargeAmount
    ){

    }
}
