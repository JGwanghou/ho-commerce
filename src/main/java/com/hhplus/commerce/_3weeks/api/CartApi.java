package com.hhplus.commerce._3weeks.api;

import com.hhplus.commerce._3weeks.api.dto.request.CartItemRequest;
import com.hhplus.commerce._3weeks.application.CartUseCase;
import com.hhplus.commerce._3weeks.domain.product.Product;
import com.hhplus.commerce._3weeks.domain.product.ProductService;
import com.hhplus.commerce._3weeks.infra.cart.cartitem.CartItemEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
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
    @GetMapping("{userId}")
    public ResponseEntity<List<CartItemEntity>> findCart(@PathVariable Long userId){
        List<CartItemEntity> cartProducts = cartUseCase.findCartProducts(userId);
        return ResponseEntity.ok(cartProducts);
    }


    /**
     *  TODO : 장바구니 추가
     */
    @Tag(name = "장바구니 추가 API", description = "장바구니 상품추가 API입니다.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CartItemEntity> addCart(@RequestBody CartItemRequest cartItemRequest){
        CartItemEntity cartItemEntity = cartUseCase.addCart(cartItemRequest.getUser_id(), cartItemRequest.getItem().getProductId(), cartItemRequest.getItem().getProduct_qunantity());
        return ResponseEntity.ok(cartItemEntity);
    }

    /**
     *  TODO : 장바구니 삭제
     */
    @DeleteMapping
    @Tag(name = "장바구니 삭제 API", description = "장바구니 상품삭제 API입니다.")
    public ResponseEntity<?> productInfo(@RequestBody List<Long> productIds){
        cartUseCase.remove(productIds);
        return ResponseEntity.ok().body("장바구니 전체 상품 삭제가 완료되었습니다.");
    }
}
