package vn.com.anhTuan.commons.mapper;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.constraints.NotNull;
import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.lang.annotation.Target;

@ConditionalOnProperty(
        name = "spring.datasource.driver-class-name",
        havingValue = "com.mysql.cj.Driver",
        matchIfMissing = false
)
public class ReferenceMapper {
    @PersistenceContext
    private EntityManager entityManager;

    @ObjectFactory
    public <T> T map(@NotNull final String id, @TargetType final Class<T> clazz) {
        return entityManager.getReference(clazz, id);
    }

    @ObjectFactory
    public <T> T map(@NotNull final Long id, @TargetType final Class<T> clazz) {
        return entityManager.getReference(clazz, id);
    }


}
