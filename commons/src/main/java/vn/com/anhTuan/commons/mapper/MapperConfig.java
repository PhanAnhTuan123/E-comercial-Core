package vn.com.anhTuan.commons.mapper;

import org.springframework.context.annotation.Bean;

public class MapperConfig {

    @Bean
    public ReferenceMapper referenceMapper() {
        return new ReferenceMapper();
    };
}
