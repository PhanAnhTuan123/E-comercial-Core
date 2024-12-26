package vn.com.anhTuan.query_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import vn.com.anhTuan.commons.cqrs.aggregate.OrderAggregate;
import vn.com.anhTuan.query_service.entity.Order;
import vn.com.anhTuan.query_service.entity.OrderProduct;
import vn.com.anhTuan.query_service.response.OrderResponse;

import java.util.List;
import java.util.Set;

@Mapper(uses = UserMapper.class)
public interface OrderMapper {

    @Mapping(source = "userId", target = "user")
    Order toOrder(OrderAggregate aggregate);

    @Mapping(source = "userId", target = "user")
    void toOrder(@MappingTarget Order order, OrderAggregate aggregate);

    OrderResponse toOrderResponse(Order order);

    default List<OrderResponse.OrderProductResponse> setOrderProductToListOrderProductResponse(
            Set<OrderProduct> orderProducts) {
        return orderProducts.stream().map(orderProduct -> OrderResponse.OrderProductResponse.builder()
                .id(orderProduct.getProduct().getId().toString())
                .name(orderProduct.getProduct().getName())
                .price(orderProduct.getProduct().getPrice())
                .quantity(orderProduct.getQuantity())
                .build()
        ).toList();
    }

}
