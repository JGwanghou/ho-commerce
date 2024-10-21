package com.hhplus.commerce._3weeks.api;

import com.hhplus.commerce._3weeks.api.dto.request.OrderRequest;
import com.hhplus.commerce._3weeks.api.dto.response.OrderResponse;
import com.hhplus.commerce._3weeks.domain.user.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserApi {

    private final UserService userService;

    @Tag(name = "유저 잔액 충전 API", description = "잔액 충전 API입니다.")
    @PatchMapping("{userId}/charge")
    public ResponseEntity<OrderResponse> charge(@PathVariable Long userId, @RequestBody Long point) {

        userService.(userId, point);

        return ResponseEntity.ok().body(new OrderResponse(orderId, "SUCCESS"));
    }
}
