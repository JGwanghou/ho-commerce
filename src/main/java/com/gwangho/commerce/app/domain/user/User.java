package com.gwangho.commerce.app.domain.user;

import com.gwangho.commerce.app.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String hpNo;

    @Builder
    public User(Long id, String name, String hpNo) {
        this.id = id;
        this.name = name;
        this.hpNo = hpNo;
    }
}
