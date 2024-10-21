package com.hhplus.commerce._3weeks.domain.user;

import static org.junit.jupiter.api.Assertions.*;

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

        List<Long> prices = List.of(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L);

        for (int i = 0; i < threadCount; i++) {
            int idx = i;
            executorService.submit(() -> {
                try {
                    userService.payment(1L, prices.get(idx));
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        assertEquals(9945, userService.getUserInfo(USER_ID).getPoint());
    }


}

