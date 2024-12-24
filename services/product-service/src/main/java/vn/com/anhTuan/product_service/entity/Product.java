package vn.com.anhTuan.product_service.entity;

import jakarta.persistence.*;
import lombok.*;
import vn.com.anhTuan.commons.persistence.BaseEntity;

import java.math.BigDecimal;

@Entity
@Table
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseEntity {

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "description",nullable = false)
    private String description;

    @Column(name = "image", nullable = false)
    private Long quantity;

    @Column(name = "price",nullable = false)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id",nullable = false)
    private Category category;


}
