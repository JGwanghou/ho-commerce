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

    public Point chargePoint(Long userId, PointCommand.ChargePoint charge) {
        Point point = pointReaderRepository.findByIdOrThrow(userId);
        point.charge(userId, PointType.CHARGE, charge.chargeAmount());

        return point;
    }
}
