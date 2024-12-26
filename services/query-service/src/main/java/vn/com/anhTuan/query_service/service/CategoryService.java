package vn.com.anhTuan.query_service.service;


import vn.com.anhTuan.commons.cqrs.aggregate.CategoryAggregate;

public interface CategoryService {

    void createCategory(CategoryAggregate aggregate);

    void updateCategory(CategoryAggregate aggregate);

}
