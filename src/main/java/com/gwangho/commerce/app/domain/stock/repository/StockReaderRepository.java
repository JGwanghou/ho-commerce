package com.gwangho.commerce.app.domain.stock.repository;

import com.gwangho.commerce.app.domain.stock.Stock;

import java.util.List;

public interface StockReaderRepository {
    Stock findByProductId(Long productId);
    List<Stock> findByProductIdIn(List<Long> Ids);
}
