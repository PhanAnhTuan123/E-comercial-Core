package vn.com.anhTuan.product_service.service;

import vn.com.anhTuan.commons.response.RestResponse;
import vn.com.anhTuan.product_service.dto.request.CreateCategoryRequest;
import vn.com.anhTuan.product_service.dto.request.UpdateCategoryRequest;
import vn.com.anhTuan.product_service.dto.response.CategoryResponse;

public interface CategoryService {

    RestResponse<CategoryResponse> createCategory(CreateCategoryRequest request);

    RestResponse<CategoryResponse> updateCategory(Long id, UpdateCategoryRequest request);
}
