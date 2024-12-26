package vn.com.anhTuan.query_service.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import vn.com.anhTuan.commons.cqrs.aggregate.ProductAggregate;
import vn.com.anhTuan.query_service.entity.Product;

@Mapper(uses = CategoryMapper.class)
public interface ProductMapper {

    @Mapping(source = "categoryId", target = "category")
    Product toProduct(ProductAggregate aggregate);

    @Mapping(source = "categoryId", target = "category")
    void toProduct(@MappingTarget Product product, ProductAggregate aggregate);

}
