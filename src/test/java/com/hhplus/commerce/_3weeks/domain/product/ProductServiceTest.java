package com.hhplus.commerce._3weeks.domain.product;

import com.hhplus.commerce._3weeks.api.dto.request.OrderProductsRequest;
import com.hhplus.commerce._3weeks.infra.product.ProductUpdater;
import com.hhplus.commerce._3weeks.infra.product.stock.ProductStockEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductReader productReader;

    @Mock
    private ProductUpdater productUpdater;

    @InjectMocks
    private ProductService productService;

    @Test
    void 상품_목록() {
        Product prod1 = new Product(1L, "초코파이", 2000, 10);
        Product prod2 = new Product(2L, "새송이버섯", 3000, 25);

        when(productReader.readProductAll()).thenReturn(List.of(prod1,prod2));

        List<Product> products = productService.readProductAll();

        assertEquals(2, products.size());
        assertEquals("초코파이", products.get(0).getName());
        assertEquals("새송이버섯", products.get(1).getName());
    }

    @Test
    void 주문한_상품목록() {
        Product prod1 = new Product(1L, "초코파이", 2000, 10);
        Product prod2 = new Product(2L, "새송이버섯", 3000, 25);

        List<Long> productIds = List.of(prod1.getId(), prod2.getId());

        when(productReader.readProductByIds(productIds)).thenReturn(List.of(prod1,prod2));

        List<Product> products = productService.readProductByIds(productIds);

        assertEquals(2, products.size());
        assertEquals("초코파이", products.get(0).getName());
        assertEquals("새송이버섯", products.get(1).getName());
    }

    @Test
    void 주문한_수량_재고차감() {
        Product prod1 = new Product(1L, "초코파이", 2000, 10);
        Product prod2 = new Product(2L, "새송이버섯", 3000, 25);

        List<OrderProductsRequest> products = List.of(
                new OrderProductsRequest(1L, 3),
                new OrderProductsRequest(2L, 2)
        );

        List<ProductStockEntity> stockList = List.of(
                new ProductStockEntity(1L, 7),
                new ProductStockEntity(2L, 23)
        );

        when(productUpdater.updateStock(products)).thenReturn(stockList);

        List<ProductStockEntity> stockEntities = productService.decreaseStock(products);

        assertEquals(7, stockEntities.get(0).getStock());
        assertEquals(23, stockEntities.get(1).getStock());
    }

    @Test
    void 상품_상세_정보_조회() {
        Long productId = 1L;

        Product prod = new Product(1L, "초코파이", 2000, 10);
        when(productReader.readProductDetail(productId)).thenReturn(prod);

        Product result = productService.readProductDetail(productId);

        assertEquals(1L, result.getId());
        assertEquals("초코파이", result.getName());
        assertEquals(2000, result.getPrice());
        assertEquals(10, result.getStock());
    }
}