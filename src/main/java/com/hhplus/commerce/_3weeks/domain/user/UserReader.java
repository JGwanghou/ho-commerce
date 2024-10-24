package com.hhplus.commerce._3weeks.domain.user;

import com.hhplus.commerce._3weeks.common.exception.UserNotFoundException;
import com.hhplus.commerce._3weeks.infra.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserReader {
    private final UserRepository userRepository;

    public UserEntity getUserInfo(Long id) {
        return userRepository.getUserInfo(id);
    }
}
