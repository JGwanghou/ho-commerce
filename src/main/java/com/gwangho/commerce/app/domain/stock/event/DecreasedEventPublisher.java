package com.gwangho.commerce.app.domain.stock.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Component
@RequiredArgsConstructor
@Slf4j
public class DecreasedEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public void success(DecreasedSucceedEvent event) {
        log.info("DecreasedEventPublisher Transaction 상태: {}", TransactionSynchronizationManager.isActualTransactionActive());

        applicationEventPublisher.publishEvent(event);
    }

    public void fail(DecreasedFailedEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
}
