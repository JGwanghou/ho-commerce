package com.gwangho.commerce.app.domain.product.repository;

import com.gwangho.commerce.app.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<Product, Long> {

}