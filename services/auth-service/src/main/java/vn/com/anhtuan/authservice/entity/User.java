package vn.com.anhtuan.authservice.entity;

import jakarta.persistence.*;
import lombok.*;
import vn.com.anhTuan.commons.persistence.BaseEntity;

import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "\"user\"")
@Getter
@Setter
@With
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "role_id", nullable = false)
    )
    private Set<Role> roles = new HashSet<>();

}
