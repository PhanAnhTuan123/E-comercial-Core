package vn.com.anhTuan.order_service.entity;

import jakarta.persistence.*;
import lombok.*;
import vn.com.anhTuan.commons.persistence.BaseEntity;

@Entity
@Table(name = "order_item")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem extends BaseEntity {

    @Column(name = "product_id",nullable = false)
    private Long productId;

    @Column(name = "quantity",nullable = false)
    private Long quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id",nullable = false)
    private Order order;
}
