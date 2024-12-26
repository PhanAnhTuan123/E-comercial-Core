package vn.com.anhTuan.query_service.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.com.anhTuan.commons.cqrs.aggregate.ProductAggregate;
import vn.com.anhTuan.commons.exception.ResourceNotFoundException;
import vn.com.anhTuan.query_service.entity.Product;
import vn.com.anhTuan.query_service.mapper.ProductMapper;
import vn.com.anhTuan.query_service.repository.ProductRepository;
import vn.com.anhTuan.query_service.service.ProductService;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    @Override
    public void createProduct(ProductAggregate aggregate) {
        productRepository.save(productMapper.toProduct(aggregate));
    }

    @Override
    public void updateProduct(ProductAggregate aggregate) {
        Product product = productRepository.findById(aggregate.id())
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", aggregate.id()));
        productMapper.toProduct(product, aggregate);
        productRepository.save(product);
    }
}
