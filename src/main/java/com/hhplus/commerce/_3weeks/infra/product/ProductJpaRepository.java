package com.hhplus.commerce._3weeks.infra.product;

import com.hhplus.commerce._3weeks.domain.product.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {

    @Query("SELECT " +
                "new com.hhplus.commerce._3weeks.domain.product.Product(p.id, p.name, p.price, ps.stock) " +
            "FROM ProductEntity p " +
            "LEFT JOIN ProductStockEntity ps " +
            "ON p.id = ps.productId")
    List<Product> findAllWithStock();

    @Query("SELECT " +
            "new com.hhplus.commerce._3weeks.domain.product.Product(p.id, p.name, p.price, ps.stock) " +
            "FROM ProductEntity p " +
            "LEFT JOIN ProductStockEntity ps " +
            "ON p.id = ps.productId " +
            "WHERE p.id IN :productIds")
    List<Product> findByIdsWithStock(List<Long> productIds);

    @Query("SELECT " +
            "new com.hhplus.commerce._3weeks.domain.product.Product(p.id, p.name, p.price, COALESCE(ps.stock, 0)) " +
            "FROM ProductEntity p " +
            "LEFT JOIN ProductStockEntity ps " +
            "ON p.id = ps.productId " +
            "WHERE p.id = :id")
    Product findByIdWithStock(@Param("id") Long id);


    @Query("SELECT " +
            "new com.hhplus.commerce._3weeks.domain.product.Product(p.name, SUM(oi.quantity)) " +
            "FROM ProductEntity p " +
            "JOIN OrderItemEntity oi " +
            "ON p.id = oi.product_id " +
            "WHERE oi.create_at BETWEEN :startDate AND :endDate " +
            "GROUP BY oi.product_id " +
            "ORDER BY SUM(oi.quantity) DESC "
    )
    List<Product> findProductPopulars(@Param("startDate") LocalDateTime startDate,
                                      @Param("endDate") LocalDateTime endDate,
                                      Pageable pageable);
}
