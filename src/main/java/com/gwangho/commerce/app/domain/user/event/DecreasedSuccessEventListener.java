package com.gwangho.commerce.app.domain.user.event;

import com.gwangho.commerce.app.domain.payment.service.PaymentCommand;
import com.gwangho.commerce.app.domain.stock.event.DecreasedSucceedEvent;
import com.gwangho.commerce.app.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
@Slf4j
public class DecreasedSuccessEventListener {
    private final UserService userService;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    void onDecreaseSuccessHandler(DecreasedSucceedEvent event) {
        Long orderUserId = event.getOrder().getUserId();
        BigDecimal price = event.getOrder().getTotalPrice();

        log.info("----- 유저 ID: {}, 결제 start -----", orderUserId);
        userService.usePoint(new PaymentCommand.UsePoint(event.getOrder(), event.getOrderItems()));
        log.info("----- 유저 ID: {}, 결제 end -----", orderUserId);
    }
}
