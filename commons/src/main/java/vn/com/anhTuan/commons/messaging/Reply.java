package vn.com.anhTuan.commons.messaging;

public record Reply<ID, T> (
    ID identifier,
    T payload,
    boolean success
) {
    public static <ID, T> Reply<ID, T> success(ID identifier, T payload) {
        return new Reply<>(identifier,payload,true);
    }

    public static <ID, T> Reply<ID, T> failure(ID identifier, T payload) {
        return new Reply<>(identifier,payload,false);
    }

}
