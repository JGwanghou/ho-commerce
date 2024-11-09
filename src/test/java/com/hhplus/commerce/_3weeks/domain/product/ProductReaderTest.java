package com.hhplus.commerce._3weeks.domain.product;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductReaderTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductReader productReader;

    @Test
    void 상품_목록() {
        List<Product> list = List.of(
                new Product(1L, "신발", 2500, 30),
                new Product(2L, "사과", 2500, 30)
        );

        when(productRepository.findProductAll()).thenReturn(list);

        List<Product> result = productReader.readProductAll();

        assertEquals(2, result.size());
    }

    @Test
    void 상품_상세조회() {
        Long productId = 1L;

        Product product = new Product(1L, "순두부", 2000, 2);

        when(productRepository.findProductById(productId)).thenReturn(product);

        Product result = productReader.readProductDetail(productId);

        assertEquals("순두부", result.getProduct_name());
        assertEquals(2000, result.getPrice());
        assertEquals(2, result.getStock());
    }

    @Test
    void 인기상품_조회() {

    }

}