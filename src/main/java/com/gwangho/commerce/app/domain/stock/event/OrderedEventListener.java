package com.gwangho.commerce.app.domain.stock.event;

import com.gwangho.commerce.app.common.exception.OutOfStockException;
import com.gwangho.commerce.app.domain.order.OrderItem;
import com.gwangho.commerce.app.domain.order.event.OrderedCreatedEvent;
import com.gwangho.commerce.app.domain.product.service.ProductService;
import com.gwangho.commerce.app.domain.stock.Stock;
import com.gwangho.commerce.app.domain.stock.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderedEventListener {
    private final ProductService productService;
    private final StockService stockService;
    private final ApplicationEventPublisher applicationEventPublisher;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    void orderSuccessHandler(OrderedCreatedEvent event) {
        Long orderId = event.getOrder().getId();
        log.info("----- 주문 ID: {}, 재고차감 start -----", orderId);
        boolean flag = true;
        for(OrderItem orderItem : event.getOrderItems()){
            try {
                stockService.decrease(String.valueOf(orderItem.getProductId()), orderItem);
            } catch (OutOfStockException e){
                log.error("재고차감 실패 주문 ID {}, 실패상품: {} ", orderItem.getOrderId(),orderItem.getProductId(), e);
                flag = false;
            }
        }
        log.info("----- 주문 ID: {}, 재고차감 end -----", orderId);

        if(flag){
            log.info("----- 주문 ID: {}, 결제 이벤트 발행 start -----", orderId);
            applicationEventPublisher.publishEvent(
                    new DecreasedSuccessEvent(
                            event.getOrder(),
                            event.getOrderItems()
                    )
            );
            log.info("----- 주문 ID: {}, 결제 이벤트 발행 end -----", orderId);
        }

    }
}