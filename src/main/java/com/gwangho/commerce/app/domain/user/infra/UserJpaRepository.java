package com.gwangho.commerce.app.domain.user.infra;

import com.gwangho.commerce.app.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long> {

}
