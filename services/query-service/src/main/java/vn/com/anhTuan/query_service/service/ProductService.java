package vn.com.anhTuan.query_service.service;


import vn.com.anhTuan.commons.cqrs.aggregate.ProductAggregate;

public interface ProductService {

    void createProduct(ProductAggregate aggregate);

    void updateProduct(ProductAggregate aggregate);

}
