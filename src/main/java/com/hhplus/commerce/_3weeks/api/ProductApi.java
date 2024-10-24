package com.hhplus.commerce._3weeks.api;

import com.hhplus.commerce._3weeks.domain.product.ProductService;
import com.hhplus.commerce._3weeks.domain.product.Product;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductApi {

    private final ProductService productService;

    @Tag(name = "상품 전체조회 API", description = "상품 전체조회 API 입니다.")
    @GetMapping
    public ResponseEntity<List<Product>> productAll(){
        List<Product> products = productService.readProductAll();
        return ResponseEntity.ok(products);//
    }

    @Tag(name = "상품 상세조회 API", description = "상품 상세조회 API 입니다.")
    @GetMapping("{productId}")
    public ResponseEntity<Product> productInfo(@PathVariable Long productId){
        Product product = productService.readProductDetail(productId);
        return ResponseEntity.ok(product);
    }

    @Tag(name = "인기 상품조회 API", description = "최근 3일간 인기상품 조회 API 입니다.")
    @GetMapping("/popular")
    public ResponseEntity<List<Product>> popular(){
        List<Product> popularProducts = productService.readProductPopulars();
        return ResponseEntity.ok(popularProducts);
    }
}
