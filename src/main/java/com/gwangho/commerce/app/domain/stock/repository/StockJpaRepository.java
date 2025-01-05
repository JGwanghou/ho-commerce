package com.gwangho.commerce.app.domain.stock.repository;

import com.gwangho.commerce.app.domain.stock.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface StockJpaRepository extends JpaRepository<Stock, Long> {
    List<Stock> findByProductIdIn(List<Long> ids);
}