package com.gwangho.commerce.app.domain.stock.service;

import com.gwangho.commerce.app.domain.order.OrderItem;
import com.gwangho.commerce.app.domain.stock.Stock;
import com.gwangho.commerce.app.domain.stock.repository.StockReaderRepository;
import com.gwangho.commerce.app.infra.RedisLock;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {
    private static final Logger log = LoggerFactory.getLogger(StockService.class);
    private final StockReaderRepository stockReaderRepository;

    @RedisLock(key = "#productKey")
    public List<Stock> decrease(String productKey, OrderItem orderItem) {
        List<Stock> stocks = new ArrayList<>();
        log.info("1. {}키를 들고왔어요. {}번 쓰레드이고 {}번 상품을 {}개 살거에요.", productKey, Thread.currentThread().getName(), orderItem.getProductId(), orderItem.getCount());

        Stock stock = stockReaderRepository.findByProductId(orderItem.getProductId());
        log.info("2. {}번 상품은 {} 개 있어요. 저는 {}개 살거에요", stock.getProductId(), stock.getQuantity(), orderItem.getCount());

        stock.decrease(orderItem.getCount());
        log.info("3. {}번 상품을 {} 개 사고 {}개 남았어요.", stock.getProductId(), orderItem.getCount(), stock.getQuantity());

        stocks.add(stock);
        return stocks;
    }
}
