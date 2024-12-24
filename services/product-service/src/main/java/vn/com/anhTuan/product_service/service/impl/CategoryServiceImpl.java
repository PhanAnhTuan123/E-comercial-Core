package vn.com.anhTuan.product_service.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import vn.com.anhTuan.commons.cqrs.channel.CQRSChannel;
import vn.com.anhTuan.commons.exception.ResourceNotFoundException;
import vn.com.anhTuan.commons.messaging.Command;
import vn.com.anhTuan.commons.response.RestResponse;
import vn.com.anhTuan.product_service.dto.request.CreateCategoryRequest;
import vn.com.anhTuan.product_service.dto.request.UpdateCategoryRequest;
import vn.com.anhTuan.product_service.dto.response.CategoryResponse;
import vn.com.anhTuan.product_service.entity.Category;
import vn.com.anhTuan.product_service.mapper.CategoryMapper;
import vn.com.anhTuan.product_service.repository.CategoryRepository;
import vn.com.anhTuan.product_service.service.CategoryService;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    private final StreamBridge streamBridge;

    @Override
    public RestResponse<CategoryResponse> createCategory(CreateCategoryRequest request) {
        Category category = categoryMapper.toCategory(request);
        category = categoryRepository.save(category);
        categoryRepository.save(category);
        streamBridge.send(CQRSChannel.CREATE_CATEGORY,new Command<>(category.getId(),
                categoryMapper.toCategoryAggregate(category)));
        return RestResponse.ok(categoryMapper.toCategoryResponse(category));
    }

    @Override
    public RestResponse<CategoryResponse> updateCategory(Long id, UpdateCategoryRequest request) {
        return categoryRepository.findById(id)
                .map(category -> categoryMapper.toCategory(category,request))
                .map(categoryRepository::save)
                .map(category -> {
                    streamBridge.send(CQRSChannel.UPDATE_CATEGORY,new Command<>(category.getId(),
                            categoryMapper.toCategoryAggregate(category)));
                    return category;
                })
                .map(categoryMapper::toCategoryResponse)
                .map(RestResponse::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found","id",id));
    }
}
