package com.hhplus.commerce._3weeks.api;

import com.hhplus.commerce._3weeks.api.dto.request.UserChargeRequest;
import com.hhplus.commerce._3weeks.api.dto.response.UserChargeResponse;
import com.hhplus.commerce._3weeks.domain.user.UserService;
import com.hhplus.commerce._3weeks.infra.user.UserEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserApi {

    private final UserService userService;

    @Tag(name = "유저 잔액 조회 API", description = "잔액 조회 API입니다.")
    @GetMapping("{userId}")
    public ResponseEntity<UserEntity> findUserPointById(@PathVariable Long userId) {

        UserEntity userEntity = userService.getUserInfo(userId);

        return ResponseEntity.ok().body(userEntity);
    }

    @Tag(name = "유저 잔액 충전 API", description = "잔액 충전 API입니다.")
    @PatchMapping("{userId}/charge")
    public ResponseEntity<UserChargeResponse> charge(@PathVariable Long userId, @RequestBody UserChargeRequest request) {

        UserEntity userEntity = userService.chargePoint(userId, request.getPoint());

        return ResponseEntity.ok().body(new UserChargeResponse(userEntity.getPoint()));
    }
}
