package com.gwangho.commerce.app.domain.product.service;

import com.gwangho.commerce.app.domain.product.Product;
import com.gwangho.commerce.app.domain.product.repository.ProductReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductReaderRepository productReaderRepository;

    public List<Product> findProductsByIds(List<Long> productIds) {
        return productReaderRepository.findProductsByIds(productIds);
    }

    public Product findByIdOrProductNotFoundThrow(Long productId) {
        return productReaderRepository.findByIdOrProductNotFoundThrow(productId);
    }
}
