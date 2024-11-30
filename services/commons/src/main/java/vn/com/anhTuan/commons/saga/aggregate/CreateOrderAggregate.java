package vn.com.anhTuan.commons.saga.aggregate;

import lombok.Builder;
import lombok.Data;
import vn.com.anhTuan.commons.enumeration.OrderStatus;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class CreateOrderAggregate {
    private Long orderId;
    private Long userId;
    private BigDecimal total;
    private OrderStatus status;
    private List<OrderItem> items;

    @Data
    @Builder
    public static class OrderItem {
        private Long id;
        private Long quantity;
    }
}
