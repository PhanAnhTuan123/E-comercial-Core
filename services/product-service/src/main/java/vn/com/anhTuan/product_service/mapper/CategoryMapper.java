package vn.com.anhTuan.product_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import vn.com.anhTuan.commons.cqrs.aggregate.CategoryAggregate;
import vn.com.anhTuan.commons.mapper.ReferenceMapper;
import vn.com.anhTuan.product_service.dto.request.CreateCategoryRequest;
import vn.com.anhTuan.product_service.dto.request.UpdateCategoryRequest;
import vn.com.anhTuan.product_service.dto.response.CategoryResponse;
import vn.com.anhTuan.product_service.dto.response.ProductResponse;
import vn.com.anhTuan.product_service.entity.Category;

@Mapper(uses = ReferenceMapper.class)
public interface CategoryMapper {

    @Mapping(target = "id", ignore = true)
    Category idToCategory(Long id);

    Category toCategory(CreateCategoryRequest request);

    Category categoryToCategory(CreateCategoryRequest request);

    CategoryResponse toCategoryResponse(Category category);

    CategoryAggregate toCategoryAggregate(Category category);

    Category toCategory(@MappingTarget Category category, UpdateCategoryRequest request);

}
