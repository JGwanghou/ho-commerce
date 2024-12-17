package com.gwangho.commerce.app.domain.user.repository;

import com.gwangho.commerce.app.domain.user.User;

import java.util.Optional;

public interface UserReaderRepository {
    User findByIdOruUserNotFoundThrow(Long userId);
    Optional<User> findById(Long userId);
}
