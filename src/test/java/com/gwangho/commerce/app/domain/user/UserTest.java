package com.gwangho.commerce.app.domain.user;

import com.gwangho.commerce.app.common.exception.InsufficientBalanceException;
import com.gwangho.commerce.app.common.exception.InvalidChargeAmountException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private static final BigDecimal SUCCESS_MIN_AMOUNT = BigDecimal.valueOf(1000);

    private static final BigDecimal FAIL_MIN_AMOUNT = BigDecimal.valueOf(999);
    private static final BigDecimal FAIL_MINUS_AMOUNT = BigDecimal.valueOf(-1000);
    private static final BigDecimal FAIL_MAX_AMOUNT = BigDecimal.valueOf(1_000_001);

    private static final BigDecimal INITIAL_POINT = BigDecimal.valueOf(5000);
    private static final BigDecimal USE_POINT = BigDecimal.valueOf(5000);
    private static final BigDecimal INSUFFICIENT_DECREASE_AMOUNT = BigDecimal.valueOf(5100);

    @Test
    @DisplayName("1000원 초과 포인트 충전 성공")
    void 포인트_정상충전() {
        // when
        User user = createUser();

        User _1000WonUser = user.addPoint(SUCCESS_MIN_AMOUNT);
        // then
        assertEquals(SUCCESS_MIN_AMOUNT, _1000WonUser.getPoint());
    }

    @Test
    @DisplayName("1000원 이하 포인트 충전 시 예외 발생")
    void 포인트_최소금액_예외발생() {
        assertPointChargeFailure(FAIL_MIN_AMOUNT, InvalidChargeAmountException.class);
    }

    @Test
    @DisplayName("0원 이하 음수 충전 시 예외 발생")
    void 포인트_음수충전() {
        assertPointChargeFailure(FAIL_MINUS_AMOUNT, InvalidChargeAmountException.class);
    }

    @Test
    @DisplayName("1,000,000원 이상 충전 시 예외 발생")
    void 포인트_최대금액_예외발생() {
        assertPointChargeFailure(FAIL_MAX_AMOUNT, InvalidChargeAmountException.class);
    }

    @Test
    @DisplayName("포인트 정상 차감")
    void 포인트_사용() {
        User _5000WonUser = createUser().addPoint(INITIAL_POINT);

        User use5000Won = _5000WonUser.decreasePoint(USE_POINT);

        assertEquals(BigDecimal.ZERO, use5000Won.getPoint());
    }

    @Test
    @DisplayName("0원 이하 포인트 차감 시 예외 발생")
    void 포인트_음수사용() {
        User _5000WonUser = createUser().addPoint(INITIAL_POINT);

        assertThrows(
                InvalidChargeAmountException.class,
                () -> _5000WonUser.decreasePoint(FAIL_MINUS_AMOUNT)
        );
    }

    @Test
    @DisplayName("잔액 부족 시 예외 발생")
    void 포인트_잔액부족_예외발생() {
        User _5000WonUser = createUser().addPoint(INITIAL_POINT);

        assertAll(
                () -> assertThrows(
                        InsufficientBalanceException.class,
                        () -> _5000WonUser.decreasePoint(INSUFFICIENT_DECREASE_AMOUNT)
                ),
                () -> assertEquals(
                        INITIAL_POINT,
                        _5000WonUser.getPoint()
                )
        );
    }

    private User createUser() {
        return User.builder()
                .id(100L)
                .name("김테스트")
                .hpNo("01012345678")
                .build();
    }

    private void assertPointChargeFailure(BigDecimal amount, Class<? extends Exception> exception) {
        User initUser = createUser();

        assertAll(
                () -> assertThrows(
                        exception,
                        () -> initUser.addPoint(amount)
                ),
                () -> assertEquals(
                        BigDecimal.ZERO,
                        initUser.getPoint()
                )
        );
    }
}