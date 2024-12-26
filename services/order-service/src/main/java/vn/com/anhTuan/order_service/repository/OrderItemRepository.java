package vn.com.anhTuan.order_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vn.com.anhTuan.order_service.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> , JpaSpecificationExecutor<OrderItem> {

}
