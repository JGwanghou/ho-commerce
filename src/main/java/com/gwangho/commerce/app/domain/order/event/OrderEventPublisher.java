package com.gwangho.commerce.app.domain.order.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public void success(OrderCreatedEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
}
