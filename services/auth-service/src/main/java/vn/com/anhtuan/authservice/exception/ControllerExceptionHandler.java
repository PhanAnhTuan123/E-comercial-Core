package vn.com.anhtuan.authservice.exception;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vn.com.anhTuan.commons.exception.ProblemDetailsBuilder;

import java.net.URI;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler({CallNotPermittedException.class})
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ProblemDetail callNotPermittedExceptionHandler(CallNotPermittedException e) {
        return ProblemDetailsBuilder.statusAndDetail(HttpStatus.SERVICE_UNAVAILABLE,e.getMessage())
                .type(URI.create("https://problems.anhTuan.com/service-unavailable"))
                .title("Service Unavailable")
                .build();
    }
}
