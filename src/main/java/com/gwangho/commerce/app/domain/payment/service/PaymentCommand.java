package com.gwangho.commerce.app.domain.payment.service;

import com.gwangho.commerce.app.domain.order.Order;
import com.gwangho.commerce.app.domain.order.OrderItem;

import java.math.BigDecimal;
import java.util.List;

public class PaymentCommand {

    public record ChargePoint(
            BigDecimal chargeAmount
    ) {
    }

    public record UsePoint(
            Order order,
            List<OrderItem> orderItemList
    ) {
    }
}
