package vn.com.anhTuan.query_service.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import vn.com.anhTuan.query_service.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findOneByCode(String code);
}
