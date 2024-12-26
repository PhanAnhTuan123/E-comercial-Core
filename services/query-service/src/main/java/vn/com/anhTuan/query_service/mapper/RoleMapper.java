package vn.com.anhTuan.query_service.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.com.anhTuan.commons.mapper.ReferenceMapper;
import vn.com.anhTuan.query_service.entity.Role;

@Mapper(uses = ReferenceMapper.class)
public interface RoleMapper {

    @Mapping(target = "id", ignore = true)
    Role idToRole(Long id);

}
