package vn.com.anhTuan.query_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import vn.com.anhTuan.commons.mapper.MapperConfig;
import vn.com.anhTuan.commons.security.SecurityConfig;

@Import({
		SecurityConfig.class,
		MapperConfig.class
})
@SpringBootApplication
public class QueryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(QueryServiceApplication.class, args);
	}

}
