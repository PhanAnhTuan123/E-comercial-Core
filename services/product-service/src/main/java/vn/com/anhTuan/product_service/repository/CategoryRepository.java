package vn.com.anhTuan.product_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vn.com.anhTuan.product_service.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> , JpaSpecificationExecutor<Category> {
}
