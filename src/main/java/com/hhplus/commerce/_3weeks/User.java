package com.hhplus.commerce._3weeks;

import lombok.Getter;

@Getter
public class User {
    private Long id;
    private int point;

    public User(Long id, int point) {
        this.id = id;
        this.point = point;
    }

    public void pointValid(int total_price) {
        if (this.point < total_price) {
            throw new IllegalStateException("잔고부족");
        }
    }

    public void usePoint(int price) {
        this.point -= price;
    }
}
