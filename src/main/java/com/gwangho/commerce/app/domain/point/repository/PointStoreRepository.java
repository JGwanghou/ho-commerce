package com.gwangho.commerce.app.domain.point.repository;

import com.gwangho.commerce.app.domain.point.Point;

public interface PointStoreRepository {
    Point save(Point entity);
}
