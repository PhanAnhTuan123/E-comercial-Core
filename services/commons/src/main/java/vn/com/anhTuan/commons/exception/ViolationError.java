package vn.com.anhTuan.commons.exception;

import java.util.List;

public record ViolationError (
        String field,
        List<String> messages
        ) {}
