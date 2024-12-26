package vn.com.anhTuan.product_service.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import vn.com.anhTuan.commons.response.RestResponse;
import vn.com.anhTuan.product_service.dto.request.CreateCategoryRequest;
import vn.com.anhTuan.product_service.dto.request.UpdateCategoryRequest;
import vn.com.anhTuan.product_service.dto.response.CategoryResponse;
import vn.com.anhTuan.product_service.service.CategoryService;

import java.net.URI;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class ProductController {
    private final CategoryService categoryService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RestResponse<CategoryResponse>> createCategory(@RequestBody @Valid
                                                                         CreateCategoryRequest request) {
        RestResponse<CategoryResponse> response = categoryService.createCategory(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(response.data().id()).toUri();
        return ResponseEntity.created(location).body(response);
    }

    @PutMapping(
            value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RestResponse<CategoryResponse>> updateCategory(@PathVariable Long id,
                                                                         @RequestBody @Valid
                                                                         UpdateCategoryRequest request) {
        return ResponseEntity.ok(categoryService.updateCategory(id, request));
    }

}
