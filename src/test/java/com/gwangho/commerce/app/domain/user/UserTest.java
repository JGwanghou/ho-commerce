package com.gwangho.commerce.app.domain.user;

import com.gwangho.commerce.app.common.exception.InvalidChargeAmountException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User common;

    @BeforeEach
    void setUser() {
        // given
        common = User.builder()
                .id(100L)
                .name("김테스트")
                .hpNo("01012345678")
                .build();
    }

    @Test
    @DisplayName("1000원 초과 포인트 충전 성공")
    void 포인트_정상충전() {
        // when
        User user = common.addPoint(BigDecimal.valueOf(1000));

        // then
        assertEquals(BigDecimal.valueOf(1000), user.getPoint());
    }
    @Test
    @DisplayName("1000원 이하 포인트 충전 시 예외 발생")
    void 포인트_최소금액_예외발생() {
        // then
        assertAll(
                () -> assertThrows(
                        InvalidChargeAmountException.class,
                        () -> common.addPoint(BigDecimal.valueOf(999))
                ),
                () -> assertEquals(
                        BigDecimal.ZERO,
                        common.getPoint()
                )
        );
    }

    @Test
    @DisplayName("0원 이하 음수 충전 시 예외 발생")
    void 포인트_음수충전() {
        // then
        assertAll(
                () -> assertThrows(
                        InvalidChargeAmountException.class,
                        () -> common.addPoint(BigDecimal.valueOf(-1000))
                ),
                () -> assertEquals(
                        BigDecimal.ZERO,
                        common.getPoint()
                )
        );
    }

    @Test
    @DisplayName("1,000,000원 이상 충전 시 예외 발생")
    void 포인트_최대금액_예외발생() {
        // then
        assertAll(
                () -> assertThrows(
                        InvalidChargeAmountException.class,
                        () -> common.addPoint(BigDecimal.valueOf(1_000_001))
                ),
                () -> assertEquals(
                        BigDecimal.ZERO,
                        common.getPoint()
                )
        );
    }
}