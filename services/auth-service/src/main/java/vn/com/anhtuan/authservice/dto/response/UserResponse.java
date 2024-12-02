package vn.com.anhtuan.authservice.dto.response;


import lombok.Builder;
import org.springframework.lang.Nullable;
import vn.com.anhTuan.commons.enumeration.OrderStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record UserResponse (
        String id,
        Instant createdAt,
        Instant updatedAt,
        @Nullable
        String createdBy,
        @Nullable
        String updatedBy,
        String username,
        BigDecimal balance,
        List<RoleResponse> roles,
        List<OrderResponse> orders
) {
    @Builder
    public record RoleResponse (
            String id,
            String name,
            String code
    ) {}

    @Builder
    public record OrderResponse (
            String id,
            Instant createdAt,
            Instant updatedAt,
            @Nullable
            String createdBy,
            @Nullable
            String updatedBy,
            BigDecimal total,
            OrderStatus status,
            List<OrderItemResponse> items
    ) {}

    @Builder
    public record OrderItemResponse (
            String id,
            Long quantity
    ) {}

}
