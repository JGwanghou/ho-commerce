package com.hhplus.commerce._3weeks.api;

import com.hhplus.commerce._3weeks.api.dto.request.OrderRequest;
import com.hhplus.commerce._3weeks.api.dto.response.OrderResponse;
import com.hhplus.commerce._3weeks.application.OrderUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderApi {
    private final OrderUseCase orderUseCase;

    @Tag(name = "상품 주문 및 결제 API", description = "상품주문 & 결제 API입니다.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<OrderResponse> order(@RequestBody OrderRequest request) {
        Long orderId = orderUseCase.order(request);
        return ResponseEntity.ok().body(new OrderResponse(orderId, "SUCCESS"));
    }
}
