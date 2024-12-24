package vn.com.anhTuan.product_service.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import vn.com.anhTuan.commons.cqrs.channel.CQRSChannel;
import vn.com.anhTuan.commons.exception.ResourceNotFoundException;
import vn.com.anhTuan.commons.messaging.Command;
import vn.com.anhTuan.commons.response.RestResponse;
import vn.com.anhTuan.product_service.dto.request.CreateProductRequest;
import vn.com.anhTuan.product_service.dto.response.ProductResponse;
import vn.com.anhTuan.product_service.entity.Product;
import vn.com.anhTuan.product_service.mapper.ProductMapper;
import vn.com.anhTuan.product_service.repository.ProductRepository;
import vn.com.anhTuan.product_service.service.ProductService;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    private final StreamBridge streamBridge;


    @Override
    public RestResponse<ProductResponse> createProduct(CreateProductRequest request) {
        Product product = productMapper.toProduct(request);
        productRepository.save(product);
        streamBridge.send(CQRSChannel.CREATE_PRODUCT, MessageBuilder.withPayload(
                new Command<>(product.getId(),productMapper.toAggregate(product))
        ).build()
        );
        return RestResponse.created(productMapper.toProductResponse(product));
    }

    @Override
    public void reduceQuantity(Long id, Long amount) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product","id",id));
        if(product.getQuantity() < amount) {
            throw new RuntimeException("Product with id"+ id + "not enough quantity");
        }
        product.setQuantity(product.getQuantity() - amount);
        productRepository.save(product);
        streamBridge.send(CQRSChannel.UPDATE_PRODUCT, MessageBuilder.withPayload(
                new Command<>(product.getId(),productMapper.toAggregate(product))
        ).build());
    }

    @Override
    public void compensateQuantity(Long id, Long amount) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product","id",id));
        product.setQuantity(product.getQuantity() + amount);
        productRepository.save(product);
        streamBridge.send(CQRSChannel.UPDATE_PRODUCT, MessageBuilder.withPayload(
                new Command<>(product.getId(),productMapper.toAggregate(product))
        ).build());
    }
}
