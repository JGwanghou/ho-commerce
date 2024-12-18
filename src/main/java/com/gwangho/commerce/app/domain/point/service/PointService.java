package com.gwangho.commerce.app.domain.point.service;

import com.gwangho.commerce.app.domain.point.Point;
import com.gwangho.commerce.app.domain.point.enums.PointType;
import com.gwangho.commerce.app.domain.point.repository.PointStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PointService {
    private final PointStoreRepository pointStoreRepository;

    public Point chargeHistoryInsert(Long userId, PointCommand.ChargePoint charge) throws Exception {
        Point point = Point.builder()
                .userId(userId)
                .type(PointType.CHARGE)
                .amount(charge.chargeAmount())
                .build();

        return pointStoreRepository.save(point);
    }

    public Point useHistoryInsert(Long userId, PointCommand.UsePoint use) throws Exception {
        Point point = Point.builder()
                .userId(userId)
                .type(PointType.USE)
                .amount(use.chargeAmount())
                .build();

        return pointStoreRepository.save(point);
    }
}
