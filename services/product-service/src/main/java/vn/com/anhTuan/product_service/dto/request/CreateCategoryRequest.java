package vn.com.anhTuan.product_service.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateCategoryRequest(
        @NotBlank
        String name,
        @NotBlank
        String slug
) {}
