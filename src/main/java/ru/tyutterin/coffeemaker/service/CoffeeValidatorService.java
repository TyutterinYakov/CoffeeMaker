package ru.tyutterin.coffeemaker.service;

import ru.tyutterin.coffeemaker.dto.NewCoffeeDto;
import ru.tyutterin.coffeemaker.model.entity.CoffeeMaker;


public interface CoffeeValidatorService {
    void checkTheAmount(NewCoffeeDto newCoffee, CoffeeMaker coffeeMaker);
}
