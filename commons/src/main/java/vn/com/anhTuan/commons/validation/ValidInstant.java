package vn.com.anhTuan.commons.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = InstantValidator.class)
public @interface ValidInstant {

    String message() default "invalid instant value";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
