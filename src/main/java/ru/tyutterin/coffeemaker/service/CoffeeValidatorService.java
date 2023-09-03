package ru.tyutterin.coffeemaker.service;

import org.springframework.validation.FieldError;
import ru.tyutterin.coffeemaker.dto.NewCoffeeDto;
import ru.tyutterin.coffeemaker.model.entity.CoffeeMaker;

import java.util.List;

public interface CoffeeValidatorService {
    void checkTheAmount(NewCoffeeDto newCoffee, CoffeeMaker coffeeMaker);
}
