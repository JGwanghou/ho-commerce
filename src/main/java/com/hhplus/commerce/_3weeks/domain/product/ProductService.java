package com.hhplus.commerce._3weeks.domain.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductReader productReader;

    public List<Product> readProductAll() {
        return productReader.readProductAll();
    }

    public Product readProductDetail(Long productId) {
        return productReader.readProductDetail(productId);
    }
}
