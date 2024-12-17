package com.gwangho.commerce.app.application;

import com.gwangho.commerce.app.api.point.PointViewResponse;
import com.gwangho.commerce.app.domain.point.service.PointCommand;
import com.gwangho.commerce.app.domain.point.service.PointService;
import com.gwangho.commerce.app.domain.user.User;
import com.gwangho.commerce.app.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PointFacade {
    private final UserService userService;
    private final PointService pointService;

    public PointViewResponse getUserPointInto(Long userId) {
        User user = userService.findByIdOruUserNotFoundThrow(userId);

        return PointViewResponse.builder()
                .userId(user.getId())
                .name(user.getName())
                .hpNo(user.getHpNo())
                .chargeAmount(user.getPoint())
                .build();
    }

    public PointViewResponse charge(String idempotencyKey, Long userId, PointCommand.ChargePoint charge) throws Exception {
        User user = userService.addPoint(userId, charge); // 상태변경
        pointService.historyInsert(userId, charge);

        return PointViewResponse.builder()
                .idempotencyKey(idempotencyKey)
                .userId(user.getId())
                .name(user.getName())
                .hpNo(user.getHpNo())
                .chargeAmount(user.getPoint())
                .build();
    }
}
