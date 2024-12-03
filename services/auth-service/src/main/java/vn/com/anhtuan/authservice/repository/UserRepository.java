package vn.com.anhtuan.authservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.com.anhtuan.authservice.entity.User;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u from User u JOIN FETCH u.roles WHERE u.username =:username")
    Optional<User> findOneByUsernameWithRoles(String username);

    @Query("SELECT u from User u JOIN FETCH u.roles WHERE u.id =:id")
    Optional<User> findByIdWithRoles(Long id);

}
