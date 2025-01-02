package com.gwangho.commerce.app.domain.user.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsePointEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public void success(UsePointSucceedEvent event) {
        applicationEventPublisher.publishEvent(event);
    }

    public void fail(UsePointFailedEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
}
