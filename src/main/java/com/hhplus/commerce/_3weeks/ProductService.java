package com.hhplus.commerce._3weeks;

import com.hhplus.commerce._3weeks.api.request.OrderProductsRequest;
import com.hhplus.commerce._3weeks.infra.ProductRepository;
import com.hhplus.commerce._3weeks.infra.entity.ProductEntity;
import com.hhplus.commerce._3weeks.infra.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product getProductInfo(Long productId) {
        return ProductMapper.toDto(productRepository.getProduct(productId));
    }

    // 구매요청 상품 총 가격 계산
    public int calcTotalPrice(List<OrderProductsRequest> products) {
        return products.stream()
                .mapToInt(reqeustProducts -> {
                    Product productInfo = getProductInfo(reqeustProducts.getProduct_id());
                    return Math.toIntExact(productInfo.getPrice() * reqeustProducts.getBuy_count());
                })
                .sum();
    }
}
