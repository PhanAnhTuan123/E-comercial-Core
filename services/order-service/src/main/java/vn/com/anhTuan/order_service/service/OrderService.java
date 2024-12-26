package vn.com.anhTuan.order_service.service;

import vn.com.anhTuan.commons.response.ListResponse;
import vn.com.anhTuan.commons.response.RestResponse;
import vn.com.anhTuan.order_service.dto.request.CreateOrderRequest;
import vn.com.anhTuan.order_service.dto.response.OrderResponse;

public interface OrderService {
    RestResponse<ListResponse<OrderResponse>> getListOrder(int page, int size, Long userId, boolean all, boolean failure);

    RestResponse<OrderResponse> createOrder(CreateOrderRequest request);

}
