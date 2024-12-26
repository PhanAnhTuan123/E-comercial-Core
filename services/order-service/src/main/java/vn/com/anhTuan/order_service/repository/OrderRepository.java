package vn.com.anhTuan.order_service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import vn.com.anhTuan.order_service.entity.Order;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {
    @Query("SELECT u from Order u join u.items where u.id =:id")
    Optional<Order> findByIdWithItems(Long id);

    @Query("select u from Order u JOIN fetch u.items where u.userId =:userId")
    Page<Order> findALlByUserIdWithOrderItems(Long userId, Pageable pageable);

}
