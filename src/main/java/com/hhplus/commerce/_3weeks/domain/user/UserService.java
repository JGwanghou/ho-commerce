package com.hhplus.commerce._3weeks.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUserInfo(Long id) {
        return User.from(userRepository.getUserInfo(id));
    }
}
