package com.hhplus.commerce._3weeks.api;

import com.hhplus.commerce._3weeks.api.dto.request.CartItemRequest;
import com.hhplus.commerce._3weeks.application.CartUseCase;
import com.hhplus.commerce._3weeks.domain.product.Product;
import com.hhplus.commerce._3weeks.domain.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/carts")
public class CartApi {

    private final CartUseCase cartUseCase;

    /**
     *  TODO : 장바구니 조회
     */
//    @GetMapping
//    public ResponseEntity<List<Product>> productAll(){
//
//    }


    /**
     *  TODO : 장바구니 추가 (userId기준 Cart에 없다면 생성, 있다면 CartItem에 상품만 추가)
     */
    @PostMapping
    public void productInfo(CartItemRequest cartItemRequest){

        cartUseCase.addCart(cartItemRequest.getUser_id(), cartItemRequest.getItems());

    }

    /**
     *  TODO : 장바구니 삭제
     */
//    @GetMapping("{productId}")
//    public ResponseEntity<Product> productInfo(@PathVariable Long productId){
//
//    }
}
