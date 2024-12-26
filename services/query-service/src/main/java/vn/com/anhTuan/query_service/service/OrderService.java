package vn.com.anhTuan.query_service.service;


import vn.com.anhTuan.commons.cqrs.aggregate.OrderAggregate;
import vn.com.anhTuan.commons.response.ListResponse;
import vn.com.anhTuan.commons.response.RestResponse;
import vn.com.anhTuan.query_service.response.OrderResponse;

import java.math.BigDecimal;

public interface OrderService {

    RestResponse<ListResponse<OrderResponse>> getListOrder(int page, int size, boolean all, BigDecimal price);

    void createOrder(OrderAggregate aggregate);

    void updateOrder(OrderAggregate aggregate);

}
