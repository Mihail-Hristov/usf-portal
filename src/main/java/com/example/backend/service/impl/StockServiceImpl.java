package com.example.backend.service.impl;

import com.example.backend.model.entity.Stock;
import com.example.backend.repository.StockRepository;
import com.example.backend.service.StockService;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;

    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public void saveStock(Stock stock) {
        stockRepository.save(stock);
    }
}
