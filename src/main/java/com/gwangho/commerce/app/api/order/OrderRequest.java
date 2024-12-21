package com.gwangho.commerce.app.api.order;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderRequest {
    @NotNull private Long userId;
    @NotNull private List<OrderCreateItem> orderCreateItems;
    @NotNull private BigDecimal paymentAmount;
}
