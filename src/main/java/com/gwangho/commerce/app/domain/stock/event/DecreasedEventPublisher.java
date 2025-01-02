package com.gwangho.commerce.app.domain.stock.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DecreasedEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public void success(DecreasedSucceedEvent event) {
        applicationEventPublisher.publishEvent(event);
    }

    public void fail(DecreasedFailedEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
}
