package com.hhplus.commerce._3weeks.infra.order.event;

import com.hhplus.commerce._3weeks.domain.order.event.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderEventListener implements ApplicationListener<OrderCreatedEvent> {
    @Override
    public void onApplicationEvent(OrderCreatedEvent event) {
        log.info("주문완료! 알림톡을 전송합니다 유저 id {} ", event.getUserId());
    }
}
