package vn.com.anhtuan.authservice.client.dto.response;

import org.springframework.lang.Nullable;
import vn.com.anhTuan.commons.enumeration.OrderStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record OrderResponse(
        String id,
        Instant createdAt,
        Instant updatedAt,
        @org.springframework.lang.Nullable
        String createdBy,
        @Nullable
        String updatedBy,
        BigDecimal total,
        OrderStatus status,
        List<OrderItemResponse> items
) {

    public record OrderItemResponse(
            String productId,
            Long quantity
    ) {}

}
