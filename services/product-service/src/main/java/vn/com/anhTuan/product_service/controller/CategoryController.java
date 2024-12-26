package vn.com.anhTuan.product_service.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import vn.com.anhTuan.commons.response.RestResponse;
import vn.com.anhTuan.product_service.dto.request.CreateProductRequest;
import vn.com.anhTuan.product_service.dto.response.ProductResponse;
import vn.com.anhTuan.product_service.service.ProductService;

import java.net.URI;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class CategoryController {
    private final ProductService productService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RestResponse<ProductResponse>> createProduct(@RequestBody @Valid
                                                                       CreateProductRequest request) {
        RestResponse<ProductResponse> response = productService.createProduct(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(response.data().id()).toUri();
        return ResponseEntity.created(location).body(response);
    }
}
