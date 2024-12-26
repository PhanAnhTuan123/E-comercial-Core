package vn.com.anhTuan.order_service.dto.response;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Null;
import vn.com.anhTuan.commons.enumeration.OrderStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

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
    List<OrderItemResponse> item
) {

    public record OrderItemResponse (
            String productId,
            Long quantity
    ) {}
}
