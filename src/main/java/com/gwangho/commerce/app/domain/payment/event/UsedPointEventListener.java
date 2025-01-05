package com.gwangho.commerce.app.domain.payment.event;

import com.gwangho.commerce.app.common.exception.OutOfStockException;
import com.gwangho.commerce.app.domain.order.OrderItem;
import com.gwangho.commerce.app.domain.order.event.OrderedCreatedEvent;
import com.gwangho.commerce.app.domain.order.service.OrderService;
import com.gwangho.commerce.app.domain.payment.service.PaymentCommand;
import com.gwangho.commerce.app.domain.payment.service.PaymentService;
import com.gwangho.commerce.app.domain.stock.service.StockService;
import com.gwangho.commerce.app.domain.user.event.UsePointFailedEvent;
import com.gwangho.commerce.app.domain.user.event.UsePointSucceedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class UsedPointEventListener {
    private final PaymentService paymentService;
    private final OrderService orderService;
    private final StockService stockService;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    void success(UsePointSucceedEvent event) throws Exception {
        paymentService.useHistoryInsert(
                event.getOrder().getUserId(),
                new PaymentCommand.UsePoint(event.getOrder(), event.getOrderItems())
        );
    }

    void fail(UsePointFailedEvent event) {

    }
}
