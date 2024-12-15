package com.gwangho.commerce.app.domain.point.infra;

import com.gwangho.commerce.app.domain.point.repository.PointStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PointCoreStoreRepository implements PointStoreRepository {
    private final PointJpaRepository userHistoryJpaRepository;


}
