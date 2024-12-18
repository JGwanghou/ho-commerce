package com.gwangho.commerce.app.domain.point.service;

import java.math.BigDecimal;

public class PointCommand {

    public record ChargePoint(
            BigDecimal chargeAmount
    ) {
    }

    public record UsePoint(
            BigDecimal chargeAmount
    ) {
    }
}
