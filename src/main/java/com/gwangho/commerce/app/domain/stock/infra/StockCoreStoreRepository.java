package com.gwangho.commerce.app.domain.stock.infra;

import com.gwangho.commerce.app.domain.stock.Stock;
import com.gwangho.commerce.app.domain.stock.repository.StockJpaRepository;
import com.gwangho.commerce.app.domain.stock.repository.StockStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class StockCoreStoreRepository implements StockStoreRepository {
    private final StockJpaRepository stockJpaRepository;

    @Override
    public Stock save(Stock stock) {
        return stockJpaRepository.save(stock);
    }

    @Override
    public List<Stock> saveAll(List<Stock> stocks) {
        return stockJpaRepository.saveAll(stocks);
    }
}
