package com.gwangho.commerce.app.domain.user.infra;

import com.gwangho.commerce.app.common.UserNotFoundException;
import com.gwangho.commerce.app.domain.user.User;
import com.gwangho.commerce.app.domain.user.repository.UserReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserCoreReaderRepository implements UserReaderRepository {
    private final UserJpaRepository userJpaRepository;

    @Override
    public User findByIdOrThrow(Long userId) {
        return userJpaRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public Optional<User> findById(Long userId) {
        return userJpaRepository.findById(userId);
    }
}
