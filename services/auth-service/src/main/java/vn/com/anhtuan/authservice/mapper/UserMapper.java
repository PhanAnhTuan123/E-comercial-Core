package vn.com.anhtuan.authservice.mapper;

import org.mapstruct.Mapper;
import vn.com.anhTuan.commons.cqrs.aggregate.UserAggregate;
import vn.com.anhTuan.commons.persistence.BaseEntity;
import vn.com.anhtuan.authservice.dto.request.SignUpRequest;
import vn.com.anhtuan.authservice.dto.response.UserResponse;
import vn.com.anhtuan.authservice.entity.Role;
import vn.com.anhtuan.authservice.entity.User;

import java.util.List;
import java.util.Set;

@Mapper
public interface UserMapper {

    User toUser(SignUpRequest request);

    UserAggregate toAggregateUser(User user);

    UserResponse toUserResponse(User user);

    default  List<Long> setRolesToListIds(Set<Role> roles) {
        return roles.stream().map(BaseEntity::getId).toList();
    }

    default List<UserResponse.RoleResponse> setRolesToListRoleResponse(Set<Role> roles) {
        return roles.stream().map(role -> UserResponse.RoleResponse.builder()
                .id(role.getId().toString())
                .name(role.getName())
                .code(role.getCode())
                .build()
        ).toList();
    }

}
