package com.hhplus.commerce._3weeks.infra.product;

import com.hhplus.commerce._3weeks.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {

    @Query("SELECT " +
                "new com.hhplus.commerce._3weeks.domain.product.Product(p.id, p.productName, p.price, ps.stock) " +
            "FROM ProductEntity p " +
            "LEFT JOIN ProductStockEntity ps " +
            "ON p.id = ps.productId")
    List<Product> findAllWithStock();

    @Query("SELECT " +
            "new com.hhplus.commerce._3weeks.domain.product.Product(p.id, p.productName, p.price, ps.stock) " +
            "FROM ProductEntity p " +
            "LEFT JOIN ProductStockEntity ps " +
            "ON p.id = ps.productId " +
            "WHERE p.id = :id")
    Product findByIdWithStock(@Param("id") Long id);
}
