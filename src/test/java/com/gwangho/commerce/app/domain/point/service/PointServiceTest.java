package com.gwangho.commerce.app.domain.point.service;

import com.gwangho.commerce.app.annotation.AcceptanceTest;
import com.gwangho.commerce.app.domain.point.Point;
import com.gwangho.commerce.app.domain.point.enums.PointType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

@AcceptanceTest
class PointServiceTest {

    @Autowired
    private PointService pointService;

    @Test
    @DisplayName("히스토리 생성")
    void 충전_히스토리_생성() throws Exception {
        Long userId = 1L;
        PointCommand.ChargePoint chargePoint = new PointCommand.ChargePoint(BigDecimal.valueOf(1000));

        Point point = pointService.chargeHistoryInsert(userId, chargePoint);

        assertAll(
                () -> assertEquals(userId, point.getId()),
                () -> assertEquals(PointType.CHARGE, point.getType()),
                () -> assertEquals(chargePoint.chargeAmount(), point.getAmount())
        );
    }
}