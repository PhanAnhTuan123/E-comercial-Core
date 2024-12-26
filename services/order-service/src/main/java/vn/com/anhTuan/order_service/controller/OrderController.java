package vn.com.anhTuan.order_service.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import vn.com.anhTuan.commons.response.ListResponse;
import vn.com.anhTuan.commons.response.RestResponse;
import vn.com.anhTuan.order_service.dto.request.CreateOrderRequest;
import vn.com.anhTuan.order_service.dto.response.OrderResponse;
import vn.com.anhTuan.order_service.service.OrderService;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RestResponse<ListResponse<OrderResponse>>> getListOrder(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam Long userId,
            @RequestParam(required = false) boolean all,
            @RequestParam(required = false) boolean failure,
            @RequestParam(defaultValue = "0") int delay
    ) throws InterruptedException {
        log.info("Receive get list order request");
        if (delay > 0)
            Thread.sleep(delay);
        return ResponseEntity.ok(orderService.getListOrder(page, size, userId, all, failure));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RestResponse<OrderResponse>> createOrder(@RequestBody @Valid
                                                                   CreateOrderRequest request) {
        RestResponse<OrderResponse> response = orderService.createOrder(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(response.data().id()).toUri();
        return ResponseEntity.created(location).body(response);
    }
}
