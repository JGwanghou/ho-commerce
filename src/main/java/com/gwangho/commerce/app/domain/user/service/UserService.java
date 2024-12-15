package com.gwangho.commerce.app.domain.user.service;

import com.gwangho.commerce.app.domain.user.User;
import com.gwangho.commerce.app.domain.user.repository.UserReaderRepository;
import com.gwangho.commerce.app.domain.user.repository.UserStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserReaderRepository userReaderRepository;
    private final UserStoreRepository userStoreRepository;

    public User findByIdOrThrow(Long userId) {
        return userReaderRepository.findByIdOrThrow(userId);
    }
}
