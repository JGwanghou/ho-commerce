package com.gwangho.commerce.app.domain.user.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public void success(PaymentSucceedEvent event) {
        applicationEventPublisher.publishEvent(event);
    }

    public void fail(PaymentFailedEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
}
