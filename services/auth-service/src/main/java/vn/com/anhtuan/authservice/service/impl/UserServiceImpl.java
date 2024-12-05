package vn.com.anhtuan.authservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.com.anhTuan.commons.messaging.Command;
import vn.com.anhTuan.commons.response.RestResponse;
import vn.com.anhtuan.authservice.client.OrderServiceClient;
import vn.com.anhtuan.authservice.client.dto.response.OrderResponse;
import vn.com.anhtuan.authservice.dto.request.SignUpRequest;
import vn.com.anhtuan.authservice.dto.response.UserResponse;
import vn.com.anhtuan.authservice.entity.Role;
import vn.com.anhtuan.authservice.entity.User;
import vn.com.anhtuan.authservice.mapper.UserMapper;
import vn.com.anhtuan.authservice.repository.RoleRepository;
import vn.com.anhtuan.authservice.repository.UserRepository;
import vn.com.anhtuan.authservice.service.UserService;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import static vn.com.anhTuan.commons.cqrs.channel.CQRSChannel.CREATED_USER;
import static vn.com.anhTuan.commons.cqrs.channel.CQRSChannel.UPDATE_USER;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final OrderServiceClient orderServiceClient;

    private final UserMapper userMapper;

    private final StreamBridge streamBridge;

    @Override
    public RestResponse<Void> createUser(SignUpRequest request) {
        Role role = roleRepository.findOneByCode("USER")
                .orElseThrow(() -> new RuntimeException("Role not found"));
        User user = userMapper.toUser(request)
                .withPassword(passwordEncoder.encode(request.password()))
                .withBalance(BigDecimal.ZERO)
                .withRoles(Set.of(role));

        userRepository.save(user);
        streamBridge.send(CREATED_USER, MessageBuilder.withPayload(
                new Command<>(user.getId(),userMapper.toAggregateUser(user))
        ).build());

        return RestResponse.created(null);
    }

    @Override
    public RestResponse<UserResponse> getOneUser(Long id, boolean failure, int delay) {
        List<OrderResponse> orders = orderServiceClient.getListOrder(id, true, failure, delay).data().items();

        return userRepository.findByIdWithRoles(id)
                .map(user -> userMapper.toUserResponse(user)
                        .withOrders(orders.stream().map(order -> UserResponse.OrderResponse.builder()
                                        .id(order.id())
                                        .createdAt(order.createdAt())
                                        .updatedAt(order.updatedAt())
                                        .createdBy(order.createdBy())
                                        .updatedBy(order.updatedBy())
                                        .total(order.total())
                                        .status(order.status())
                                        .items(order.items().stream().map(item -> UserResponse.OrderResponse
                                                .OrderItemResponse.builder()
                                                .id(item.productId())
                                                .quantity(item.quantity())
                                                .build()).toList()
                                        ).build()
                                ).toList()
                        )
                )
                .map(RestResponse::ok)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public void debitBalance(Long id, BigDecimal amount) {
        User user = userRepository.findByIdWithRoles(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if(user.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Balance is lower than zero");
        }
        user.setBalance(user.getBalance().subtract(amount));
        userRepository.save(user);
        streamBridge.send(UPDATE_USER, MessageBuilder.withPayload(
                new Command<>(user.getId(), userMapper.toAggregateUser(user))
        ).build()
        );
    }

}
