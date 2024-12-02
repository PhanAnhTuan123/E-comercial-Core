package vn.com.anhtuan.authservice.client;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.com.anhTuan.commons.response.ListResponse;
import vn.com.anhTuan.commons.response.RestResponse;
import vn.com.anhtuan.authservice.client.dto.response.OrderResponse;

public interface OrderServiceClient {

    String BASE = "/order";

    @GetMapping(value = BASE + "/orders",produces = MediaType.APPLICATION_JSON_VALUE)
    RestResponse<ListResponse<OrderResponse>> getListorder(
            @RequestParam Long userId,
            @RequestParam(required = false) boolean all,
            @RequestParam(required = false) boolean failure,
            @RequestParam(required = false) int delay
    );

}
