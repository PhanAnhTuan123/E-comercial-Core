package vn.com.anhTuan.order_service.saga.createorder;

public enum CreateOrderSagaState {
    CREATED,
    COMPLETED,
    CANCELED,
    INVENTORY_REDUCED,
    INVENTORY_ERROR,
    PAYMENT_PROCESSED,
    PAYMENT_ERROR,
    INVENTORY_COMPENSATED
}
