package com.gwangho.commerce.app.domain.stock.service;

import com.gwangho.commerce.app.domain.order.Order;
import com.gwangho.commerce.app.domain.order.OrderItem;
import com.gwangho.commerce.app.domain.stock.Stock;
import com.gwangho.commerce.app.domain.stock.event.DecreasedEventPublisher;
import com.gwangho.commerce.app.domain.stock.event.DecreasedFailedEvent;
import com.gwangho.commerce.app.domain.stock.event.DecreasedSucceedEvent;
import com.gwangho.commerce.app.domain.stock.repository.StockReaderRepository;
import com.gwangho.commerce.app.infra.lock.RedisLock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StockService {
    private final StockReaderRepository stockReaderRepository;
    private final DecreasedEventPublisher eventPublisher;

    @RedisLock(key = "#productKey")
    public void decrease(String productKey, OrderItem orderItem) {
        Stock stock = stockReaderRepository.findByProductId(orderItem.getProductId());
        stock.decrease(orderItem.getCount());
    }

    @Transactional
    public void successDecrease(Order order, List<OrderItem> orderItem) {
        log.info("Transaction 상태: {}", TransactionSynchronizationManager.isActualTransactionActive());

        eventPublisher.success(new DecreasedSucceedEvent(order,orderItem));
    }

    public void failDecrease(Order order, List<OrderItem> orderItem) {
        eventPublisher.fail(new DecreasedFailedEvent(order,orderItem));
    }
}
