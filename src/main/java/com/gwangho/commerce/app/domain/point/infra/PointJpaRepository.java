package com.gwangho.commerce.app.domain.point.infra;

import com.gwangho.commerce.app.domain.point.Point;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointJpaRepository extends JpaRepository<Point, Long> {

}
