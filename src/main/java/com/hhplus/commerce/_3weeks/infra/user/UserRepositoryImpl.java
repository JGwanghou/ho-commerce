package com.hhplus.commerce._3weeks.infra.user;

import com.hhplus.commerce._3weeks.common.exception.UserNotFoundException;
import com.hhplus.commerce._3weeks.domain.product.Product;
import com.hhplus.commerce._3weeks.domain.user.User;
import com.hhplus.commerce._3weeks.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepository userJpaRepository;

    @Override
    public UserEntity getUserInfo(Long id) {
        return userJpaRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("해당 유저가 존재하지않습니다."));
    }

    @Override
    public UserEntity payment(UserEntity userEntity) {
        return userJpaRepository.save(userEntity);
    }

    @Override
    public UserEntity charge(UserEntity userEntity) {
        return userJpaRepository.save(userEntity);
    }
}
