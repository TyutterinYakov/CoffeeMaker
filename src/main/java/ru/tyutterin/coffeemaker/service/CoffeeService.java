package ru.tyutterin.coffeemaker.service;

import ru.tyutterin.coffeemaker.dto.NewCoffeeDto;
import ru.tyutterin.coffeemaker.model.entity.Coffee;
import ru.tyutterin.coffeemaker.model.entity.CoffeeType;

public interface CoffeeService {


    CoffeeType getType();
    Coffee build(NewCoffeeDto newCoffee);

}
