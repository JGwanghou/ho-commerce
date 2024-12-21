package com.gwangho.commerce.app.common;

import com.gwangho.commerce.app.domain.product.Product;
import com.gwangho.commerce.app.domain.product.repository.ProductJpaRepository;
import com.gwangho.commerce.app.domain.stock.Stock;
import com.gwangho.commerce.app.domain.stock.repository.StockJpaRepository;
import com.gwangho.commerce.app.domain.user.User;
import com.gwangho.commerce.app.domain.user.infra.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@Profile({"local", "test"})
@RequiredArgsConstructor
@Slf4j
public class ProductDataInitializer implements CommandLineRunner {
    private final UserJpaRepository userJpaRepository;
    private final ProductJpaRepository productRepository;
    private final StockJpaRepository stockJpaRepository;

    @Override
    public void run(String... args) throws Exception {
        userJpaRepository.deleteAll();
        User user = User.builder()
                .id(1L)
                .name("김테스트")
                .hpNo("010-1234-1234")
                .build();

        userJpaRepository.save(user);
        // 기존 데이터 삭제 (옵션)
        productRepository.deleteAll();

        // 상품 데이터 초기화
        List<Product> products = List.of(
                new Product(1L,"맥북 프로", BigDecimal.valueOf(100)),
                new Product(2L,"아이폰 A", BigDecimal.valueOf(500)),
                new Product(3L,"그램 노트북", BigDecimal.valueOf(200)),
                new Product(4L,"티셔츠XL", BigDecimal.valueOf(150)),
                new Product(5L,"신발", BigDecimal.valueOf(300)),
                new Product(6L,"무늬가방", BigDecimal.valueOf(400))
        );

        productRepository.saveAll(products);

        List<Stock> stocks = List.of(
                new Stock(1L, 1L,100L),
                new Stock(2L, 2L,100L),
                new Stock(3L, 3L,100L),
                new Stock(4L, 4L,100L),
                new Stock(5L, 5L,100L),
                new Stock(6L, 6L,100L)
        );
        stockJpaRepository.saveAll(stocks);

        log.info("local product Insert!");
    }
}
