package com.gwangho.commerce.app.domain.stock.service;

import com.gwangho.commerce.app.annotation.AcceptanceTest;
import com.gwangho.commerce.app.common.exception.OutOfStockException;
import com.gwangho.commerce.app.domain.order.OrderItem;
import com.gwangho.commerce.app.domain.product.Product;
import com.gwangho.commerce.app.domain.product.repository.ProductStoreRepository;
import com.gwangho.commerce.app.domain.stock.Stock;
import com.gwangho.commerce.app.domain.stock.repository.StockReaderRepository;
import com.gwangho.commerce.app.domain.stock.repository.StockStoreRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;


@AcceptanceTest
class StockServiceTest {
    private static final Logger log = LoggerFactory.getLogger(StockServiceTest.class);

    @Autowired
    private ProductStoreRepository productStoreRepository;

    @Autowired
    private StockService stockService;

    @Autowired
    private StockReaderRepository stockReaderRepository;

    @Autowired
    private StockStoreRepository stockStoreRepository;

    @BeforeEach
    void setUp() {

    }

    @Test
    void _1개의_상품_50명_구매시도() throws InterruptedException {
        int numberOfThreads = 51;
        // order
        OrderItem orderItem = new OrderItem(1L, 1L, 1L, BigDecimal.valueOf(1000));
        String key = String.valueOf(orderItem.getProductId());

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        AtomicInteger success = new AtomicInteger();
        AtomicInteger fail = new AtomicInteger();
        for (int i = 0; i < numberOfThreads; i++) {
            executorService.submit(() -> {
                try {
                    stockService.decrease(key, orderItem);
                    success.getAndIncrement();
                } catch (OutOfStockException e){
                    fail.getAndIncrement();
                }finally {
                    latch.countDown();
                }
            });
        }
        latch.await();

        Stock stock = stockReaderRepository.findByProductId(orderItem.getProductId());
        assertEquals(0L, stock.getQuantity(), "총 재고는 0개 이어야합니다.");
        assertEquals(50, success.get(), "재고 차감 성공횟수는 50번 이어야 합니다.");
        assertEquals(1, fail.get());
    }

    @Test
    void _2개상품_10명_동시구매() throws InterruptedException {
        int numberOfThreads = 10;

        /*
            orderId, productId, count, amount
            10명의 사용자가 하나의 상품만 주문하는 것이 아닌 여러개를 주문한다.
         */
        List<OrderItem> orderItems = Arrays.asList(
                new OrderItem(1L, 1L, 2L, BigDecimal.valueOf(1000)),
                new OrderItem(1L, 2L, 2L, BigDecimal.valueOf(1000))
        );

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            executorService.submit(() -> {
                try {
                    for (OrderItem orderItem : orderItems) {
                        stockService.decrease(String.valueOf(orderItem.getProductId()),orderItem);
                    }
                } catch (OutOfStockException e) {
                    e.getStackTrace();
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executorService.shutdown();

        Stock PRODUCT_1 = stockReaderRepository.findByProductId(1L);
        Stock PRODUCT_2 = stockReaderRepository.findByProductId(2L);

        assertEquals(30, PRODUCT_1.getQuantity(), "총 재고는 30개 이어야 합니다");
        assertEquals(30, PRODUCT_2.getQuantity(), "총 재고는 30개 이어야 합니다.");
    }
}