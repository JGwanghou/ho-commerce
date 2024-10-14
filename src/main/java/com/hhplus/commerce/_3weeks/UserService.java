package com.hhplus.commerce._3weeks;

import com.hhplus.commerce._3weeks.infra.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUserInfo(Long id) {
        return userRepository.getUserInfo(id);
    }

    public void validateUserPoints(Long userId, int totalPrice) {
        getUserInfo(userId).pointValid(totalPrice);
    }
}
