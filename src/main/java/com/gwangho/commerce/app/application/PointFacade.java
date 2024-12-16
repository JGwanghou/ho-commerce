package com.gwangho.commerce.app.application;

import com.gwangho.commerce.app.api.point.PointResponse;
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

    public PointResponse charge(Long userId, PointCommand.ChargePoint charge) {
        User user = userService.addPoint(userId, charge); // 상태변경
        pointService.historyInsert(userId, charge);

        return PointResponse.builder()
                .userId(user.getId())
                .name(user.getName())
                .hpNo(user.getHpNo())
                .chargeAmount(user.getPoint())
                .build();
    }
}
