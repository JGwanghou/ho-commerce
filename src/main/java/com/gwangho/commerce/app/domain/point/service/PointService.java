package com.gwangho.commerce.app.domain.point.service;

import com.gwangho.commerce.app.domain.point.Point;
import com.gwangho.commerce.app.domain.point.enums.PointType;
import com.gwangho.commerce.app.domain.point.repository.PointReaderRepository;
import com.gwangho.commerce.app.domain.point.repository.PointStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PointService {
    private final PointReaderRepository pointReaderRepository;
    private final PointStoreRepository pointStoreRepository;

    public Point historyInsert(Long userId, PointCommand.ChargePoint charge) throws Exception {
        return pointStoreRepository.save(
                Point.builder()
                    .userId(userId)
                    .type(PointType.CHARGE)
                    .amount(charge.chargeAmount())
                    .build()
        );
    }
}
