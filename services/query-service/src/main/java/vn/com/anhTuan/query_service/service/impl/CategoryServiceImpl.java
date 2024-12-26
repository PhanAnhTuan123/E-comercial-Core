package vn.com.anhTuan.query_service.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.com.anhTuan.commons.cqrs.aggregate.CategoryAggregate;
import vn.com.anhTuan.commons.exception.ResourceNotFoundException;
import vn.com.anhTuan.query_service.entity.Category;
import vn.com.anhTuan.query_service.mapper.CategoryMapper;
import vn.com.anhTuan.query_service.repository.CategoryRepository;
import vn.com.anhTuan.query_service.service.CategoryService;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    @Override
    public void createCategory(CategoryAggregate aggregate) {
        categoryRepository.save(categoryMapper.toCategory(aggregate));
    }

    @Override
    public void updateCategory(CategoryAggregate aggregate) {
        Category category = categoryRepository.findById(aggregate.id())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", aggregate.id()));
        categoryMapper.toCategory(category, aggregate);
        categoryRepository.save(category);
    }


}
