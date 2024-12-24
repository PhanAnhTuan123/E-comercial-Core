package vn.com.anhTuan.product_service.service;

import vn.com.anhTuan.commons.response.RestResponse;
import vn.com.anhTuan.product_service.dto.request.CreateProductRequest;
import vn.com.anhTuan.product_service.dto.response.ProductResponse;

public interface ProductService {

    RestResponse<ProductResponse> createProduct(CreateProductRequest request);

    void reduceQuantity(Long id, Long amount);

    void compensateQuantity(Long id, Long amount);
}
