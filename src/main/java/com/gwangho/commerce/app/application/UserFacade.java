package com.gwangho.commerce.app.application;

import com.gwangho.commerce.app.api.user.UserPointViewResponse;
import com.gwangho.commerce.app.domain.payment.service.PaymentService;
import com.gwangho.commerce.app.domain.payment.service.PaymentCommand;
import com.gwangho.commerce.app.domain.user.User;
import com.gwangho.commerce.app.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UserFacade {
    private final UserService userService;
    private final PaymentService paymentService;

    public UserPointViewResponse getUserPointInto(Long userId) {
        User user = userService.findByIdOruUserNotFoundThrow(userId);

        return UserPointViewResponse.builder()
                .userId(user.getId())
                .name(user.getName())
                .hpNo(user.getHpNo())
                .chargeAmount(user.getPoint())
                .build();
    }

    @Transactional
    public UserPointViewResponse charge(String idempotencyKey, Long userId, PaymentCommand.ChargePoint charge) throws Exception {
        User user = userService.addPoint(userId, charge); // 상태변경
        paymentService.chargeHistoryInsert(userId, charge);

        return UserPointViewResponse.builder()
                .idempotencyKey(idempotencyKey)
                .userId(user.getId())
                .name(user.getName())
                .hpNo(user.getHpNo())
                .chargeAmount(user.getPoint())
                .build();
    }
}
