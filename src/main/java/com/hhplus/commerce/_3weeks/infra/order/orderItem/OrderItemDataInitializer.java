package com.hhplus.commerce._3weeks.infra.order.orderItem;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class OrderItemDataInitializer implements CommandLineRunner {
    private final JdbcTemplate jdbcTemplate;
    private final Random random = new Random();
    private static final int TOTAL_RECORDS = 1_000_000;
    private static final int BATCH_SIZE = 10_000;

    @Override
    public void run(String... args) throws Exception {
        List<OrderItemEntity> batchData = new ArrayList<>(BATCH_SIZE);

        for (int i = 0; i < TOTAL_RECORDS; i++) {
            OrderItemEntity orderItem = OrderItemEntity.builder()
                    .order_id((long) (random.nextInt(10) + 1))  // order_id: 1~10 랜덤
                    .product_id((long) (random.nextInt(10) + 1)) // product_id: 1~10 랜덤
                    .quantity(random.nextInt(10) + 1)           // quantity: 1~10 랜덤
                    .build();
            orderItem.setCreate_at(LocalDateTime.now().minusDays(random.nextInt(5)));
            batchData.add(orderItem);

            if (batchData.size() >= BATCH_SIZE) {
                saveAll(batchData);
                batchData.clear();
            }
        }

        // 남은 데이터 처리
        if (!batchData.isEmpty()) {
            saveAll(batchData);
        }
    }


    private void saveAll(List<OrderItemEntity> orderItems) {
        String sql = "INSERT INTO ORDER_ITEM (order_id, product_id, quantity, create_at) VALUES (?, ?, ?, ?)";

        try {
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
        } catch (Exception e) {
            throw new RuntimeException("Failed to insert batch data", e);
        }
    }
}
