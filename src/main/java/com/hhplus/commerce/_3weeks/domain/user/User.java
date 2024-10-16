package com.hhplus.commerce._3weeks.domain.user;

import com.hhplus.commerce._3weeks.infra.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class User {
    private Long id;
    private String name;
    private Long point;

    private User(Long id, String name, Long point) {
        this.id = id;
        this.name = name;
        this.point = point;
    }


    public static User from(UserEntity userEntity) {
        return new User(userEntity.getId(), userEntity.getName(), userEntity.getPoint());
    }
}
