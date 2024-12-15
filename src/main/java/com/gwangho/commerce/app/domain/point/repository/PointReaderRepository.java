package com.gwangho.commerce.app.domain.point.repository;

import com.gwangho.commerce.app.domain.point.Point;

import java.util.Optional;

public interface PointReaderRepository {
    Point findByIdOrThrow(Long userId);
    Optional<Point> findById(Long userId);
}
