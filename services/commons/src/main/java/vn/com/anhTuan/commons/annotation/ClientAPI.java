package vn.com.anhTuan.commons.annotation;

import vn.com.anhTuan.commons.enumeration.Client;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ClientAPI {
    Client[] value() default {};
}
