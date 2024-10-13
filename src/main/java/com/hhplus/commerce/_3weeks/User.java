package com.hhplus.commerce._3weeks;

import lombok.Getter;

@Getter
public class User {
    private int id;
    private int point;

    public User(Integer id, int point) {
        this.id = id;
        this.point = point;
    }

    public void usePoint(int price) {
        if (this.point < price) {
            throw new IllegalStateException("잔액이 부족합니다.");
        }

        this.point -= price;
    }
}
