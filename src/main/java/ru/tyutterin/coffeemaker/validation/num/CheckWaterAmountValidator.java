package ru.tyutterin.coffeemaker.validation.num;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.tyutterin.coffeemaker.dto.NewCoffeeMakerDto;

public class CheckWaterAmountValidator implements ConstraintValidator<CheckWaterAmount, NewCoffeeMakerDto> {

    @Override
    public boolean isValid(NewCoffeeMakerDto newCoffeeMakerDto, ConstraintValidatorContext constraintValidatorContext) {
        int amountOfWaterForFlushing = newCoffeeMakerDto.getAmountOfWaterForFlushing();
        int waterCompartment = newCoffeeMakerDto.getWaterCompartment();
        return waterCompartment - amountOfWaterForFlushing >= 0;
    }
}
