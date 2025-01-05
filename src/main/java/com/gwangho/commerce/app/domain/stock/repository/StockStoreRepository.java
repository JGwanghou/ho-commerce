package com.gwangho.commerce.app.domain.stock.repository;

import com.gwangho.commerce.app.domain.stock.Stock;

import java.util.List;

public interface StockStoreRepository {
    Stock save(Stock stock);
    List<Stock> saveAll(List<Stock> stocks);
}
