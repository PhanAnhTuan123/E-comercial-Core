package vn.com.anhTuan.commons.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.Instant;

public class InstantValidator implements ConstraintValidator<ValidInstant, Instant> {

    @Override
    public void initialize(ValidInstant constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Instant value, ConstraintValidatorContext constraintValidatorContext) {
        return !Instant.MIN.equals(value);
    }
}
