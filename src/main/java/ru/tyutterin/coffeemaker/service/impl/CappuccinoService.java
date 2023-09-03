package ru.tyutterin.coffeemaker.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.tyutterin.coffeemaker.dto.NewCoffeeDto;
import ru.tyutterin.coffeemaker.exception.BadRequestException;
import ru.tyutterin.coffeemaker.model.entity.*;
import ru.tyutterin.coffeemaker.repository.*;
import ru.tyutterin.coffeemaker.service.CoffeeValidatorService;

import java.util.List;

@Service
public class CappuccinoService extends AbstractCoffeeService {

    public CappuccinoService(CoffeeMakerRepository coffeeMakerRepository, CoffeeRepository coffeeRepository,
                             List<CoffeeValidatorService> coffeeValidatorServices) {
        super(coffeeMakerRepository, coffeeRepository, coffeeValidatorServices);
    }

    @Override
    @Transactional
    public Coffee build(NewCoffeeDto newCoffee) {
        Integer portionMilk = newCoffee.getPortionMilk();
        if (portionMilk == null || portionMilk <= 0) {
            throw new BadRequestException("Check out the recipe. Cappuccino is made with milk");
        }
        return super.build(newCoffee);
    }


    @Override
    public CoffeeType getType() {
        return CoffeeType.CAPPUCCINO;
    }




}
