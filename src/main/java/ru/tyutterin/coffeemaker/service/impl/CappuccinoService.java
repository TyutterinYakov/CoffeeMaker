package ru.tyutterin.coffeemaker.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.tyutterin.coffeemaker.dto.NewCoffee;
import ru.tyutterin.coffeemaker.model.entity.*;
import ru.tyutterin.coffeemaker.repository.*;
import ru.tyutterin.coffeemaker.service.CoffeeMakerService;

@Service
public class CappuccinoService extends AbstractCoffeeService {


    public CappuccinoService(CoffeeMakerService coffeeMakerService, WaterResideRepository waterResideRepository,
                             CoffeeRepository coffeeRepository, SugarResideRepository sugarResideRepository,
                             MilkResideRepository milkResideRepository,
                             @Value("${standard.portion.sugar}") int standardPortionSugar) {
        super(coffeeMakerService, waterResideRepository, coffeeRepository, sugarResideRepository,
                milkResideRepository, standardPortionSugar);
    }

    @Override
    @Transactional
    public Coffee build(NewCoffee newCoffee) {
        if (newCoffee.getPortionMilk() == null) {
            throw new RuntimeException("Check out the recipe. Cappuccino is made with milk");
        }
        return super.build(newCoffee);
    }


    @Override
    public CoffeeType getType() {
        return CoffeeType.CAPPUCCINO;
    }




}
