package com.gwangho.commerce.app.domain.stock.service;

import com.gwangho.commerce.app.domain.order.OrderItem;
import com.gwangho.commerce.app.domain.product.Product;

import java.math.BigDecimal;
import java.util.List;

public class StockCommand {

    public record Ordered(
            List<OrderItem> orderItems
    ) {
    }

}
