package vn.com.anhTuan.order_service.entity;

import jakarta.persistence.*;
import lombok.*;
import vn.com.anhTuan.commons.enumeration.OrderStatus;
import vn.com.anhTuan.commons.persistence.BaseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order")
@Getter
@Setter
@With
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order extends BaseEntity {

    @Column(name = "user_id",nullable = false)
    private Long userId;

    @Column(name = "total",nullable = false)
    private BigDecimal total;

    @Column(name = "status",nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(mappedBy = "order", cascade =  CascadeType.ALL,orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();
}
