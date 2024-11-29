package vn.com.anhTuan.commons.messaging;

public record Command <ID, T> (
        ID identifier,
        T payload
) { }
