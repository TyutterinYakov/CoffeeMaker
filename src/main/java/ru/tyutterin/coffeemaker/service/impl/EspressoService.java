package ru.tyutterin.coffeemaker.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tyutterin.coffeemaker.dto.NewCoffee;
import ru.tyutterin.coffeemaker.model.entity.Coffee;
import ru.tyutterin.coffeemaker.model.entity.CoffeeType;
import ru.tyutterin.coffeemaker.repository.CoffeeMakerRepository;
import ru.tyutterin.coffeemaker.service.CoffeeService;

@Service
@RequiredArgsConstructor
public class EspressoService implements CoffeeService {

    private final CoffeeMakerRepository coffeeMakerRepository;

    @Override
    public Coffee build(NewCoffee newCoffee) {
        return null;
    }







    @Override
    public CoffeeType getType() {
        return CoffeeType.ESPRESSO;
    }
}
