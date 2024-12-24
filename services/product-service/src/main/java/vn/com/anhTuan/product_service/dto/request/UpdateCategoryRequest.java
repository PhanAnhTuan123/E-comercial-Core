package vn.com.anhTuan.product_service.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateCategoryRequest(
        @NotBlank
        String name,
        @NotBlank
        String slug
) {}
