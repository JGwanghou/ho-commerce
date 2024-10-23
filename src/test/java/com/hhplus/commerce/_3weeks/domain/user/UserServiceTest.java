package com.hhplus.commerce._3weeks.domain.user;

import com.hhplus.commerce._3weeks.api.dto.request.OrderProductsRequest;
import com.hhplus.commerce._3weeks.api.dto.request.OrderRequest;
import com.hhplus.commerce._3weeks.domain.product.Product;
import com.hhplus.commerce._3weeks.infra.user.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserReader userReader;

    @Mock
    private UserUpdater userUpdater;


    @InjectMocks
    private UserService userService;

    private UserEntity mockUser;

    @BeforeEach
    void setUp() {
        mockUser = UserEntity.builder()
                .id(1L)
                .name("조광호")
                .point(10000L)
                .build();
    }

    @Test
    void 유저_정보_조회() {
        when(userReader.getUserInfo(1L)).thenReturn(mockUser);

        UserEntity userInfo = userService.getUserInfo(mockUser.getId());

        assertEquals(1L, userInfo.getId());
        assertEquals("조광호", userInfo.getName());
        assertEquals(10000L, userInfo.getPoint());
    }

    @Test
    void 유저_포인트_충전() {
        UserEntity afterUser = new UserEntity(1L, "조광호", 15000L);

        when(userReader.getUserInfo(1L)).thenReturn(mockUser);
        when(userUpdater.charge(mockUser, 5000L)).thenReturn(afterUser);

        UserEntity userEntity = userService.chargePoint(mockUser.getId(), 5000L);

        assertEquals(15000, userEntity.getPoint());
    }

    @Test
    void 주문시_유저포인트_소모() {
        Product testProduct1 = new Product(1L, "스크류바", 2000, 30);
        Product testProduct2 = new Product(2L, "우유", 4000, 20);

        List<OrderProductsRequest> orderProducts = List.of(
                new OrderProductsRequest(testProduct1.getId(), 2),
                new OrderProductsRequest(testProduct2.getId(), 1)
        );

        OrderRequest orderRequest = new OrderRequest(mockUser.getId(), orderProducts, 8000L);

        UserEntity usePointUser = new UserEntity(1L, "조광호", 2000L);
        when(userUpdater.payment(mockUser, orderRequest.getPaymentPrice())).thenReturn(usePointUser);

        UserEntity result = userService.payment(mockUser.getId(), orderRequest.getPaymentPrice());

        assertEquals(2000L, result.getPoint());
    }
}