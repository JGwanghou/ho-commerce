package com.gwangho.commerce.app.domain.user.service;

import com.gwangho.commerce.app.annotation.AcceptanceTest;
import com.gwangho.commerce.app.common.exception.InsufficientBalanceException;
import com.gwangho.commerce.app.common.exception.UserNotFoundException;
import com.gwangho.commerce.app.domain.user.User;
import com.gwangho.commerce.app.domain.user.repository.UserStoreRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@AcceptanceTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserStoreRepository userStoreRepository;

    @Test
    @DisplayName("유저 조회")
    @Order(1)
    void 유저조회() {
        User user = 유저생성();

        Optional<User> result = userService.findById(user.getId());

        assertNotNull(result);
        assertEquals(user.getId(), result.get().getId());
        assertEquals(user.getName(), result.get().getName());
        assertEquals(user.getHpNo(), result.get().getHpNo());
    }

    @Test
    @DisplayName("유저 조회 예외 발생")
    @Order(2)
    void 유저조회_예외() {
        Long userId = 1L;

        assertThrows(UserNotFoundException.class,
                () -> userService.findByIdOruUserNotFoundThrow(userId)
        );
    }


    private User 유저생성() {
        User init = User.builder()
                        .id(1L)
                        .name("조테스트")
                        .hpNo("01012340000")
                    .build();

        return userStoreRepository.save(init);
    }
}