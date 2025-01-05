package com.gwangho.commerce.app.domain.user.infra;

import com.gwangho.commerce.app.domain.user.User;
import com.gwangho.commerce.app.domain.user.repository.UserStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserCoreStoreRepository implements UserStoreRepository {
    private final UserJpaRepository userJpaRepository;

    @Override
    public User save(User user) {
        return userJpaRepository.save(user);
    }
}
