package vn.com.anhTuan.order_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import vn.com.anhTuan.commons.mapper.MapperConfig;
import vn.com.anhTuan.commons.persistence.AuditingConfig;
import vn.com.anhTuan.commons.security.SecurityConfig;

@Import({
		SecurityConfig.class,
		AuditingConfig.class,
		MapperConfig.class
})
@SpringBootApplication
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

}
