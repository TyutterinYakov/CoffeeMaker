package ru.tyutterin.coffeemaker.service;

import ru.tyutterin.coffeemaker.dto.NewCoffee;
import ru.tyutterin.coffeemaker.model.entity.CoffeeMaker;

public interface CoffeeValidatorService {
    void checkTheAmount(NewCoffee newCoffee, CoffeeMaker coffeeMaker);
}
