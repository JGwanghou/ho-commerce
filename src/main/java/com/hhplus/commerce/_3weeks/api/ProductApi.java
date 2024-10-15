package com.hhplus.commerce._3weeks.api;

import com.hhplus.commerce._3weeks.domain.product.ProductService;
import com.hhplus.commerce._3weeks.domain.product.Product;
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

    /**
     *  TODO : 상품 목록 API
     */
    @GetMapping
    public ResponseEntity<List<Product>> productAll(){
        List<Product> products = productService.readProductAll();
        return ResponseEntity.ok(products);
    }


    /**
     *  TODO : 상품 조회 API
     */
    @GetMapping("{productId}")
    public ResponseEntity<Product> productInfo(@PathVariable Long productId){
        Product product = productService.readProductDetail(productId);
        System.out.println(product);
        return ResponseEntity.ok(product);
    }
}