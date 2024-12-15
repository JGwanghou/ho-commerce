package com.gwangho.commerce.app.application;

import com.gwangho.commerce.app.api.point.PointDto;
import com.gwangho.commerce.app.domain.point.Point;
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

    public PointDto.PointResponse charge(Long userId, PointCommand.ChargePoint charge) {
        User user = userService.findByIdOrThrow(userId);
        Point point = pointService.chargePoint(userId, charge);

        return PointDto.PointResponse.builder()
                .userId(user.getId())
                .name(user.getName())
                .hpNo(user.getHpNo())
                .chargeAmount(point.getAmount())
                .build();

    }
}
