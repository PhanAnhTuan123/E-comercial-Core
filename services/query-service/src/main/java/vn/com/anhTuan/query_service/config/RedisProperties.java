package vn.com.anhTuan.query_service.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("redis")
public record RedisProperties(
        String host,
        int port
) {}
