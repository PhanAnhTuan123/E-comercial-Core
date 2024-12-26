package vn.com.anhTuan.query_service.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.anhTuan.commons.cqrs.aggregate.OrderAggregate;
import vn.com.anhTuan.commons.exception.ResourceNotFoundException;
import vn.com.anhTuan.commons.response.ListResponse;
import vn.com.anhTuan.commons.response.RestResponse;
import vn.com.anhTuan.query_service.entity.Order;
import vn.com.anhTuan.query_service.entity.OrderProduct;
import vn.com.anhTuan.query_service.entity.OrderProductKey;
import vn.com.anhTuan.query_service.mapper.OrderMapper;
import vn.com.anhTuan.query_service.repository.OrderRepository;
import vn.com.anhTuan.query_service.repository.ProductRepository;
import vn.com.anhTuan.query_service.response.OrderResponse;
import vn.com.anhTuan.query_service.service.OrderService;

import java.math.BigDecimal;
import java.util.stream.Collectors;

@Service
@EnableCaching
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    private final OrderMapper orderMapper;

    @Override
    @Cacheable("orders")
    public RestResponse<ListResponse<OrderResponse>> getListOrder(int page, int size, boolean all, BigDecimal price) {
        Pageable pageable = all ? Pageable.unpaged() : PageRequest.of(page - 1, size);
        return RestResponse.ok(ListResponse.of(orderRepository
                .findAllOrderHaveProductPriceGreaterThan(price, pageable)
                .map(orderMapper::toOrderResponse))
        );
    }

    @Override
    public void createOrder(OrderAggregate aggregate) {
        Order order = orderMapper.toOrder(aggregate);
        setOrderProductsForOrder(aggregate, order);
    }

    @Override
    public void updateOrder(OrderAggregate aggregate) {
        Order order = orderRepository.findById(aggregate.id())
                .orElseThrow(() -> new ResourceNotFoundException("Order", "id", aggregate.id()));
        orderMapper.toOrder(order, aggregate);
        setOrderProductsForOrder(aggregate, order);
    }

    private void setOrderProductsForOrder(OrderAggregate aggregate, Order order) {
        order.setOrderProducts(aggregate.items().stream().map(item ->
                OrderProduct.builder()
                        .id(OrderProductKey.builder()
                                .orderId(aggregate.id())
                                .productId(item.productId())
                                .build())
                        .product(productRepository.findById(item.productId())
                                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", item.productId()))
                        )
                        .order(order)
                        .quantity(item.quantity())
                        .build()
        ).collect(Collectors.toSet()));
        orderRepository.save(order);
    }
}
