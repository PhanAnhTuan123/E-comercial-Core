package vn.com.anhtuan.authservice.dto.request;

import jakarta.validation.constraints.NotBlank;

public record SignUpRequest (
        @NotBlank String username,
        @NotBlank String password
) {}
