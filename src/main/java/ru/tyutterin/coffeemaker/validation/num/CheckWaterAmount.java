package ru.tyutterin.coffeemaker.validation.num;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
@Target(ElementType.TYPE_USE)
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = CheckWaterAmountValidator.class)
public @interface CheckWaterAmount {

    String message() default
            "There should be enough water for washing(waterCompartment - amountOfWaterForFlushing >= 0)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
