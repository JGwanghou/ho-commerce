package com.hhplus.commerce._3weeks.infra;

import com.hhplus.commerce._3weeks.infra.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {

}
