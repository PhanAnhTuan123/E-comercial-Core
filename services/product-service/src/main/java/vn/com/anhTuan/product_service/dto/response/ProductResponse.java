package vn.com.anhTuan.product_service.dto.response;

import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.time.Instant;

public record ProductResponse(
        String id,
        Instant createdAt,
        Instant updatedAt,
        @Nullable
        String createdBy,
        @Nullable
        String updatedBy,
        String name,
        String description,
        String image,
        Long quantity,
        BigDecimal price,
        CategoryResponse category
) {

    public record CategoryResponse(
            String name,
            String slug
    ) {}

}
