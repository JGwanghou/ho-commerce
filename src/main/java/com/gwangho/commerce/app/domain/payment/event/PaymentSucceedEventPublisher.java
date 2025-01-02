package com.gwangho.commerce.app.domain.payment.event;

import com.gwangho.commerce.app.domain.user.event.PaymentFailedEvent;
import com.gwangho.commerce.app.domain.user.event.PaymentSucceedEvent;
import com.gwangho.commerce.app.infra.platform.OrderAfterDataPlatForm;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentSucceedEventPublisher {
    private OrderAfterDataPlatForm orderAfterDataPlatForm;
//    https://hooks.slack.com/services/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
    public void success(PaymentSucceedEvent event) {
        orderAfterDataPlatForm.slackAlarm(event.getOrder());
    }

    public void fail(PaymentFailedEvent event) {

    }

}
