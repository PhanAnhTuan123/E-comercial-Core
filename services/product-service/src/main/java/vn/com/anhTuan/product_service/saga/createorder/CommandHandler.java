package vn.com.anhTuan.product_service.saga.createorder;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.aspectj.bridge.Message;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vn.com.anhTuan.product_service.service.ProductService;

import java.util.function.Function;

@Configuration
@RequiredArgsConstructor
public class CommandHandler {

    private final ObjectMapper objectMapper;

    private final ProductService productService;

//    @Bean
//    public Function<Message<>>



}
