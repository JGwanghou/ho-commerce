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

    public UserEntity payment(UserEntity user, Long point) {
        user.validPoint(point);

        return userRepository.payment(user);
    }

}

