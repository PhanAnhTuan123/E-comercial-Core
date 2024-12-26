package vn.com.anhTuan.query_service.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import vn.com.anhTuan.commons.cqrs.aggregate.CategoryAggregate;
import vn.com.anhTuan.commons.mapper.ReferenceMapper;
import vn.com.anhTuan.query_service.entity.Category;

@Mapper(uses = ReferenceMapper.class)
public interface CategoryMapper {

    @Mapping(target = "id", ignore = true)
    Category idToCategory(Long id);

    Category toCategory(CategoryAggregate aggregate);

    void toCategory(@MappingTarget Category category, CategoryAggregate aggregate);

}
