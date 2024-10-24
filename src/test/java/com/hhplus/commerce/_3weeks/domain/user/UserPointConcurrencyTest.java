package com.hhplus.commerce._3weeks.domain.user;

import static org.junit.jupiter.api.Assertions.*;

import com.hhplus.commerce._3weeks.domain.product.Product;
import com.hhplus.commerce._3weeks.infra.user.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;


@SpringBootTest
public class UserPointConcurrencyTest {

    @Autowired
    private UserService userService;

    private final Long USER_ID = 1L;
    private final int threadCount = 10;

    @Test
    void 유저_한명이_동시에_10번_충전시도() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        List<Long> prices = List.of(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L);

        for (int i = 0; i < threadCount; i++) {
            int idx = i;
            executorService.submit(() -> {
                try {
                    userService.chargePoint(USER_ID, prices.get(idx));
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        assertEquals(10055, userService.getUserInfo(USER_ID).getPoint());
    }

    @Test
    void 유저_한명이_동시에_10번_사용시도() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        List<Product> products = List.of(
                new Product(1L, "테스트상품1", 1, 10),
                new Product(2L, "테스트상품2", 2, 10),
                new Product(3L, "테스트상품3", 3, 10),
                new Product(4L, "테스트상품4", 4, 10),
                new Product(5L, "테스트상품5", 5, 10),
                new Product(6L, "테스트상품6", 6, 10),
                new Product(7L, "테스트상품7", 7, 10),
                new Product(8L, "테스트상품8", 8, 10),
                new Product(9L, "테스트상품9", 9, 10),
                new Product(10L, "테스트상품10", 10, 10)
        );

        List<Long> prices = List.of(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L);

        for (int i = 0; i < threadCount; i++) {
            int idx = i;
            executorService.submit(() -> {
                try {
                    userService.payment(1L, products, prices.get(idx));
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        assertEquals(9945, userService.getUserInfo(USER_ID).getPoint());
    }


}

