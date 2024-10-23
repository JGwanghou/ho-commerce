package com.hhplus.commerce._3weeks.domain.user;

import com.hhplus.commerce._3weeks.api.dto.request.OrderRequest;
import com.hhplus.commerce._3weeks.common.exception.PriceMismatchException;
import com.hhplus.commerce._3weeks.domain.product.Product;
import com.hhplus.commerce._3weeks.infra.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserUpdater {
    private final UserRepository userRepository;


    public UserEntity charge(UserEntity user, Long point) {
        user.chargePoint(point);

        return userRepository.charge(user);
    }

    public UserEntity payment(UserEntity user, List<Product> products, Long point) {
        verifyPriceMatch(products, point);
        user.validPoint(point);

        return userRepository.payment(user);
    }

    public void verifyPriceMatch(List<Product> products, Long point) {
        long orderPrice = (long) products.stream()
                .mapToInt(Product::getPrice)
                .sum();

        if(orderPrice != point) throw new PriceMismatchException("결제 금액이 상이합니다.");
    }
}

