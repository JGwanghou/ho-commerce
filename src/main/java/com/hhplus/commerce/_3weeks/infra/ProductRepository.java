package com.hhplus.commerce._3weeks.infra;

import com.hhplus.commerce._3weeks.Product;
import com.hhplus.commerce._3weeks.infra.entity.ProductEntity;


public interface ProductRepository {
    ProductEntity getProduct(Long id);
}
