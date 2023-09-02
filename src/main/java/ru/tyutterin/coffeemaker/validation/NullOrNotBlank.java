package ru.tyutterin.coffeemaker.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NullOrNotBlankValidator.class)
@Target( {ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NullOrNotBlank {
    String message() default "{String not null and isBlank}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
