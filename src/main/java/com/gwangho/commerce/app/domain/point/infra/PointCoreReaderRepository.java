package com.gwangho.commerce.app.domain.point.infra;

import com.gwangho.commerce.app.common.exception.UserNotFoundException;
import com.gwangho.commerce.app.domain.point.Point;
import com.gwangho.commerce.app.domain.point.repository.PointReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PointCoreReaderRepository implements PointReaderRepository {
    private final PointJpaRepository userHistoryJpaRepository;

    @Override
    public Point findByIdOrThrow(Long userId) {
        return userHistoryJpaRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public Optional<Point> findById(Long userId) {
        return userHistoryJpaRepository.findById(userId);
    }
}
