package com.hhplus.commerce._3weeks.infra;

import com.hhplus.commerce._3weeks.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository{

    @Override
    public User getUserInfo(Long id) {
        return new User(5L, 5000);
    }
}
