package vn.com.anhTuan.order_service.mapper;

import org.apache.catalina.security.SecurityUtil;
import org.mapstruct.Mapper;
import vn.com.anhTuan.commons.cqrs.aggregate.OrderAggregate;
import vn.com.anhTuan.commons.enumeration.OrderStatus;
import vn.com.anhTuan.commons.utils.SecurityUtils;
import vn.com.anhTuan.order_service.dto.request.CreateOrderRequest;
import vn.com.anhTuan.order_service.dto.response.OrderResponse;
import vn.com.anhTuan.order_service.entity.Order;
import vn.com.anhTuan.order_service.entity.OrderItem;

@Mapper
public interface OrderMapper {

    default Order toOder(CreateOrderRequest request) {
        Order order = Order.builder()
                .userId(SecurityUtils.getUserId())
                .total(request.total())
                .status(OrderStatus.CREATED)
                .items(request.items().stream().map(item -> OrderItem.builder()
                        .productid(Long.valueOf(item.productId()))
                        .quantity(item.quantity())
                        .build()
                ).toList())
                .build();
        order.getItems().forEach(item -> item.setOrder(order));
        return order;
    }

    OrderAggregate toAggregate(Order order);

    OrderResponse toOrderResponse(Order order);

}
