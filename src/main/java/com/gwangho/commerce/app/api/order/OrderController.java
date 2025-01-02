package com.gwangho.commerce.app.api.order;

import com.gwangho.commerce.app.application.OrderFacade;
import com.gwangho.commerce.app.domain.order.service.OrderCommand;
import com.gwangho.commerce.app.domain.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderFacade orderFacade;

    @PostMapping
    public ResponseEntity<OrderResponse> order(
            @RequestBody @Valid OrderRequest request
    ){
        Long userId = request.getUserId();
        OrderCommand.CreateOrder createOrder = new OrderCommand.CreateOrder(
                userId,
                request.getOrderCreateItems().stream()
                        .map(item -> new OrderCommand.CreateOrderItem(item.getProductId(), item.getCount(), BigDecimal.ZERO))
                        .toList(),
                request.getPaymentAmount()
        );

        OrderResponse order = orderFacade.createOrder(createOrder);

        return ResponseEntity.ok(order);
    }
}
