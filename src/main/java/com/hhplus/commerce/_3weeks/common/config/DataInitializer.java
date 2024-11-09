package com.hhplus.commerce._3weeks.common.config;

import com.hhplus.commerce._3weeks.infra.order.orderItem.OrderItemEntity;
import com.hhplus.commerce._3weeks.infra.product.ProductEntity;
import com.hhplus.commerce._3weeks.infra.product.stock.ProductStockEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final JdbcTemplate jdbcTemplate;
    private final Random random = new Random();
    private static final int TOTAL_RECORDS = 1_000_000;
    private static final int BATCH_SIZE = 10_000;

    @Override
    public void run(String... args) throws Exception {
        initializeProducts();
        initializeProductStocks();
        initializeOrderItems();
    }

    private void initializeProducts() {
        List<ProductEntity> batchData = new ArrayList<>(BATCH_SIZE);
        String[] categories = {"전자기기", "의류", "식품", "가구", "도서"};

        for (int i = 1; i <= TOTAL_RECORDS; i++) {
            ProductEntity product = ProductEntity.builder()
                    .name(generateProductName(categories[random.nextInt(categories.length)], i))
                    .price(generateRandomPrice())
                    .build();
            batchData.add(product);

            if (batchData.size() >= BATCH_SIZE) {
                saveProducts(batchData);
                batchData.clear();
            }
        }

        if (!batchData.isEmpty()) {
            saveProducts(batchData);
        }
    }

    private void initializeProductStocks() {
        List<ProductStockEntity> batchData = new ArrayList<>(BATCH_SIZE);

        for (long i = 1; i <= TOTAL_RECORDS; i++) {
            ProductStockEntity stock = ProductStockEntity.builder()
                    .productId(i)
                    .stock(random.nextInt(1000) + 1) // 1~1000 사이의 재고
                    .build();
            batchData.add(stock);

            if (batchData.size() >= BATCH_SIZE) {
                saveProductStocks(batchData);
                batchData.clear();
            }
        }

        if (!batchData.isEmpty()) {
            saveProductStocks(batchData);
        }
    }

    private void initializeOrderItems() {
        List<OrderItemEntity> batchData = new ArrayList<>(BATCH_SIZE);

        for (int i = 0; i < TOTAL_RECORDS; i++) {
            OrderItemEntity orderItem = OrderItemEntity.builder()
                    .order_id((long) (random.nextInt(10) + 1))
                    .product_id((long) (random.nextInt(TOTAL_RECORDS) + 1)) // 전체 상품 범위에서 선택
                    .quantity(random.nextInt(10) + 1)
                    .build();
            orderItem.setCreate_at(LocalDateTime.now().minusDays(random.nextInt(30))); // 30일 범위로 확장
            batchData.add(orderItem);

            if (batchData.size() >= BATCH_SIZE) {
                saveOrderItems(batchData);
                batchData.clear();
            }
        }

        if (!batchData.isEmpty()) {
            saveOrderItems(batchData);
        }
    }

    private String generateProductName(String category, int index) {
        String[] adjectives = {"최신", "프리미엄", "기본형", "프로", "울트라"};
        return String.format("%s %s 상품_%d",
                adjectives[random.nextInt(adjectives.length)],
                category,
                index
        );
    }

    private int generateRandomPrice() {
        // 1만원~100만원 사이의 가격, 100원 단위로 끊기
        return (random.nextInt(1000) + 10) * 1000;
    }

    private void saveProducts(List<ProductEntity> products) {
        String sql = "INSERT INTO PRODUCT (name, price) VALUES (?, ?)";

        jdbcTemplate.batchUpdate(sql,
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ProductEntity product = products.get(i);
                        ps.setString(1, product.getName());
                        ps.setInt(2, product.getPrice());
                    }

                    @Override
                    public int getBatchSize() {
                        return products.size();
                    }
                });
    }

    private void saveProductStocks(List<ProductStockEntity> stocks) {
        String sql = "INSERT INTO PRODUCT_STOCK (product_id, stock) VALUES (?, ?)";

        jdbcTemplate.batchUpdate(sql,
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ProductStockEntity stock = stocks.get(i);
                        ps.setLong(1, stock.getProductId());
                        ps.setInt(2, stock.getStock());
                    }

                    @Override
                    public int getBatchSize() {
                        return stocks.size();
                    }
                });
    }

    private void saveOrderItems(List<OrderItemEntity> orderItems) {
        String sql = "INSERT INTO ORDER_ITEM (order_id, product_id, quantity, create_at) VALUES (?, ?, ?, ?)";

        jdbcTemplate.batchUpdate(sql,
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        OrderItemEntity orderItem = orderItems.get(i);
                        ps.setLong(1, orderItem.getOrder_id());
                        ps.setLong(2, orderItem.getProduct_id());
                        ps.setLong(3, orderItem.getQuantity());
                        ps.setTimestamp(4, Timestamp.valueOf(orderItem.getCreate_at()));
                    }

                    @Override
                    public int getBatchSize() {
                        return orderItems.size();
                    }
                });
    }
}
