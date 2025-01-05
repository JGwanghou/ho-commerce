package com.gwangho.commerce.app.domain.user.repository;

import com.gwangho.commerce.app.domain.user.User;

public interface UserStoreRepository {
    User save(User user);
}
