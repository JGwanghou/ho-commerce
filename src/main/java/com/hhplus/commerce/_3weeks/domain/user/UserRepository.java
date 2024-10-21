package com.hhplus.commerce._3weeks.domain.user;

import com.hhplus.commerce._3weeks.domain.product.Product;
import com.hhplus.commerce._3weeks.infra.user.UserEntity;

import java.util.List;

public interface UserRepository {
    UserEntity getUserInfo(Long id);
    UserEntity payment(UserEntity userEntity);

    UserEntity charge(UserEntity userEntity);
}
