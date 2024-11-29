package vn.com.anhTuan.commons.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.URI;


public interface CommonExceptionHandler {

    Logger log = LoggerFactory.getLogger(CommonExceptionHandler.class);

    @ExceptionHandler(org.springframework.security.access.AccessDeniedException.class)
    default ProblemDetail accessDeniedExceptionHandler(AccessDeniedException accessDeniedException) {
        return ProblemDetailsBuilder.statusAndDetail(HttpStatus.FORBIDDEN, accessDeniedException.getMessage())
                .type(URI.create("https://problems.anhTuan.com/access-denied"))
                .title("Access Denied")
                .build();
    }

    // AUTHORIZED

    // NOT FOUND

    //


}
