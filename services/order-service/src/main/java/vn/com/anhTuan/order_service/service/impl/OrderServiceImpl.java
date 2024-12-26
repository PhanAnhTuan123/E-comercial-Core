package vn.com.anhTuan.order_service.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.anhTuan.commons.cqrs.channel.CQRSChannel;
import vn.com.anhTuan.commons.messaging.Command;
import vn.com.anhTuan.commons.response.ListResponse;
import vn.com.anhTuan.commons.response.RestResponse;
import vn.com.anhTuan.commons.saga.aggregate.CreateOrderAggregate;
import vn.com.anhTuan.order_service.dto.request.CreateOrderRequest;
import vn.com.anhTuan.order_service.dto.response.OrderResponse;
import vn.com.anhTuan.order_service.entity.Order;
import vn.com.anhTuan.order_service.mapper.OrderMapper;
import vn.com.anhTuan.order_service.repository.OrderRepository;
import vn.com.anhTuan.order_service.saga.createorder.CreateOrderSagaManager;
import vn.com.anhTuan.order_service.service.OrderService;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    private final CreateOrderSagaManager createOrderSagaManager;

    private final StreamBridge streamBridge;

    @Override
    public RestResponse<ListResponse<OrderResponse>> getListOrder(int page, int size, Long userId,
                                                                  boolean all, boolean failure) {
        if (failure)
            throw new RuntimeException("Exception at order service");
        Pageable pageable = all ? Pageable.unpaged() : PageRequest.of(page - 1, size);
        Page<OrderResponse> responses = orderRepository
                .findALlByUserIdWithOrderItems(userId, pageable)
                .map(orderMapper::toOrderResponse);
        return RestResponse.ok(ListResponse.of(responses));
    }

    @Override
    public RestResponse<OrderResponse> createOrder(CreateOrderRequest request) {
        Order order = orderMapper.toOder(request);
        orderRepository.save(order);
        CreateOrderAggregate aggregate = CreateOrderAggregate.builder()
                .orderId(order.getId())
                .userId(order.getUserId())
                .total(order.getTotal())
                .status(order.getStatus())
                .items(order.getItems().stream().map(item ->
                        new CreateOrderAggregate.OrderItem(item.getProductId(), item.getQuantity())
                ).toList())
                .build();
        createOrderSagaManager.putSaga(aggregate);
        streamBridge.send(CQRSChannel.CREATE_ORDER, MessageBuilder.withPayload(
                new Command<>(order.getId(), orderMapper.toAggregate(order))).build());
        return RestResponse.created(orderMapper.toOrderResponse(order));
    }
}