package com.gwangho.commerce.app.api.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderCreateItem {
    private Long productId;
    private Long count;
}
