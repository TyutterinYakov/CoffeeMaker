package ru.tyutterin.coffeemaker.service.impl;

import lombok.RequiredArgsConstructor;
import ru.tyutterin.coffeemaker.dto.NewCoffee;
import ru.tyutterin.coffeemaker.exception.NotFoundException;
import ru.tyutterin.coffeemaker.model.entity.*;
import ru.tyutterin.coffeemaker.repository.CoffeeMakerRepository;
import ru.tyutterin.coffeemaker.repository.CoffeeRepository;
import ru.tyutterin.coffeemaker.service.CoffeeService;
import ru.tyutterin.coffeemaker.service.CoffeeValidatorService;

import java.util.List;

@RequiredArgsConstructor
public abstract class AbstractCoffeeService implements CoffeeService {

    private final CoffeeMakerRepository coffeeMakerRepository;
    private final CoffeeRepository coffeeRepository;
    private final List<CoffeeValidatorService> coffeeValidatorServices;

    @Override
    public Coffee build(NewCoffee newCoffee) {
        Long coffeeMakerId = newCoffee.getCoffeeMakerId();
        CoffeeMaker coffeeMaker = coffeeMakerRepository.findByIdAndOn(coffeeMakerId).orElseThrow(() ->
                new NotFoundException(coffeeMakerId, CoffeeMaker.class));
        coffeeValidatorServices.forEach(val -> val.checkTheAmount(newCoffee, coffeeMaker));
        return coffeeRepository.save(new Coffee(newCoffee.getCoffeeType(), coffeeMaker));
    }

}
