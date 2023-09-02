package ru.tyutterin.coffeemaker.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.tyutterin.coffeemaker.dto.NewCoffee;
import ru.tyutterin.coffeemaker.model.entity.*;
import ru.tyutterin.coffeemaker.repository.*;
import ru.tyutterin.coffeemaker.service.CoffeeMakerService;
import ru.tyutterin.coffeemaker.service.CoffeeValidatorService;

import java.util.List;

@Service
public class EspressoService extends AbstractCoffeeService {

    public EspressoService(CoffeeMakerRepository coffeeMakerRepository, CoffeeRepository coffeeRepository,
                           List<CoffeeValidatorService> coffeeValidatorServices) {
        super(coffeeMakerRepository, coffeeRepository, coffeeValidatorServices);
    }

    @Override
    @Transactional
    public Coffee build(NewCoffee newCoffee) {
        if (newCoffee.getPortionMilk() != null) {
            throw new RuntimeException("Check out the recipe. Espresso is made without milk");
        }
        return super.build(newCoffee);
    }



    @Override
    public CoffeeType getType() {
        return CoffeeType.ESPRESSO;
    }
}
