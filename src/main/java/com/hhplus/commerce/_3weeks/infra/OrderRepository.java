package com.hhplus.commerce._3weeks.infra;

import com.hhplus.commerce._3weeks.api.request.OrdersRequest;

public interface OrderRepository {
    public void save(OrdersRequest ordersRequest);
}
