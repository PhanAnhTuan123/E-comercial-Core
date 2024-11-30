package vn.com.anhTuan.commons.annotation;

import ch.qos.logback.core.net.server.Client;
import vn.com.anhTuan.commons.enumeration.Micro;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface InternalAPI {
    Micro[] value() default {};
}
