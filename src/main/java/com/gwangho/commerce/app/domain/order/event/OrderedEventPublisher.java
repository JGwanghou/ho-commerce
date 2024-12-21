package com.gwangho.commerce.app.domain.order.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderedEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public void success(OrderedCreatedEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
}
