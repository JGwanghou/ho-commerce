package com.hhplus.commerce._3weeks.domain.order;

import com.hhplus.commerce._3weeks.api.dto.request.OrderProductsRequest;
import com.hhplus.commerce._3weeks.api.dto.request.OrderRequest;
import com.hhplus.commerce._3weeks.application.OrderUseCase;
import com.hhplus.commerce._3weeks.common.exception.OutOfStockException;
import com.hhplus.commerce._3weeks.domain.product.Product;
import com.hhplus.commerce._3weeks.domain.product.ProductService;
import com.hhplus.commerce._3weeks.domain.user.UserService;
import com.hhplus.commerce._3weeks.infra.user.UserEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class OrderConcurrentlyTest {

    @Autowired
    private OrderUseCase orderUseCase;

    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    private Logger log = LoggerFactory.getLogger(OrderConcurrentlyTest.class);

    @Test
    void DB락_재고_100개_상품에_101번의_주문시도() throws InterruptedException {

        int threadCount = 101;
        Long userId = 1L;
        Long productId = 1L;

        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        List<OrderProductsRequest> orderProductsRequests = List.of(
                new OrderProductsRequest(productId, 1)
        );

        OrderRequest orderRequest = new OrderRequest(1L, orderProductsRequests, 0L);

        AtomicInteger successCount = new AtomicInteger();
        AtomicInteger failCount = new AtomicInteger();

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {

                    orderUseCase.order(orderRequest);
                    successCount.getAndIncrement();

                } catch (OutOfStockException e) {

                    failCount.getAndIncrement();

                } finally {

                    latch.countDown();

                }
            });
        }

        latch.await();
        long endTime = System.currentTimeMillis();
        log.info("실행 시간 : {} milliseconds", endTime - startTime);

        Product product = productService.readProductDetail(productId);
        UserEntity userInfo = userService.getUserInfo(userId);

        assertEquals(100, successCount.get());
        assertEquals(1, failCount.get());

        assertEquals(0, product.getStock());
        assertEquals(800000, userInfo.getPoint());
    }


    @Test
    void Lettuce_재고_100개_상품에_101번의_주문시도() throws InterruptedException {

        int threadCount = 101;
        Long userId = 1L;
        Long productId = 1L;

        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        List<OrderProductsRequest> orderProductsRequests = List.of(
                new OrderProductsRequest(productId, 1)
        );

        OrderRequest orderRequest = new OrderRequest(1L, orderProductsRequests, 0L);

        AtomicInteger successCount = new AtomicInteger();
        AtomicInteger failCount = new AtomicInteger();

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {

//                    orderUseCase.orderWithLettuce(orderRequest);
                    successCount.getAndIncrement();

                } catch (OutOfStockException e) {

                    failCount.getAndIncrement();

                } finally {

                    latch.countDown();

                }
            });
        }

        latch.await();
        long endTime = System.currentTimeMillis();
        log.info("실행 시간 : {} milliseconds", endTime - startTime);

        Product product = productService.readProductDetail(productId);
        UserEntity userInfo = userService.getUserInfo(userId);

        assertEquals(100, successCount.get());
        assertEquals(1, failCount.get());

        assertEquals(0, product.getStock());
        assertEquals(800000, userInfo.getPoint());
    }

    @Test
    @DisplayName("Redisson 활용 동시성 테스트")
    void Redisson_재고_100개_상품에_101번의_주문시도() throws InterruptedException {

        int threadCount = 101;
        Long userId = 1L;
        Long productId = 1L;

        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        List<OrderProductsRequest> orderProductsRequests = List.of(
                new OrderProductsRequest(productId, 1)
        );

        OrderRequest orderRequest = new OrderRequest(1L, orderProductsRequests, 0L);

        AtomicInteger successCount = new AtomicInteger();
        AtomicInteger failCount = new AtomicInteger();

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {

//                    orderUseCase.orderWithRedisson(orderRequest);
                    successCount.getAndIncrement();

                } catch (OutOfStockException e) {

                    failCount.getAndIncrement();

                } finally {

                    latch.countDown();

                }
            });
        }

        latch.await();
        long endTime = System.currentTimeMillis();
        log.info("실행 시간 : {} milliseconds", endTime - startTime);

        Product product = productService.readProductDetail(productId);
        UserEntity userInfo = userService.getUserInfo(userId);

        assertEquals(0, product.getStock());
        assertEquals(100, successCount.get());
        assertEquals(1, failCount.get());

        assertEquals(800000, userInfo.getPoint());
    }
}
