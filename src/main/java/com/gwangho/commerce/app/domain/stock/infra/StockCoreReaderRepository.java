package com.gwangho.commerce.app.domain.stock.infra;

import com.gwangho.commerce.app.common.exception.StockNotFoundException;
import com.gwangho.commerce.app.domain.stock.Stock;
import com.gwangho.commerce.app.domain.stock.repository.StockJpaRepository;
import com.gwangho.commerce.app.domain.stock.repository.StockReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class StockCoreReaderRepository implements StockReaderRepository {
    private final StockJpaRepository stockJpaRepository;

    @Override
    public Stock findByProductId(Long productId) {
        return stockJpaRepository.findById(productId).orElseThrow(StockNotFoundException::new);
    }

    @Override
    public List<Stock> findByProductIdIn(List<Long> Ids) {
        return stockJpaRepository.findByProductIdIn(Ids);
    }

}
