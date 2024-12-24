package vn.com.anhTuan.product_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.com.anhTuan.commons.cqrs.aggregate.ProductAggregate;
import vn.com.anhTuan.product_service.dto.request.CreateProductRequest;
import vn.com.anhTuan.product_service.dto.response.ProductResponse;
import vn.com.anhTuan.product_service.entity.Product;

@Mapper(uses = CategoryMapper.class)
public interface ProductMapper {

    @Mapping(source = "categoryId",target = "category")
    Product toProduct(CreateProductRequest request);

    ProductResponse toProductResponse(Product product);

    @Mapping(source = "category.id", target = "categoryId")
    ProductAggregate toAggregate(Product product);

}
