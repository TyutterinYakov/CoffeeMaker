package ru.tyutterin.coffeemaker.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.tyutterin.coffeemaker.dto.NewCoffee;
import ru.tyutterin.coffeemaker.model.entity.*;
import ru.tyutterin.coffeemaker.repository.CoffeeRepository;
import ru.tyutterin.coffeemaker.repository.MilkResideRepository;
import ru.tyutterin.coffeemaker.repository.SugarResideRepository;
import ru.tyutterin.coffeemaker.repository.WaterResideRepository;
import ru.tyutterin.coffeemaker.service.CoffeeMakerService;

@Service
public class EspressoService extends AbstractCoffeeService {

    public EspressoService(CoffeeMakerService coffeeMakerService, MilkResideRepository milkResideRepository,
                           WaterResideRepository waterResideRepository, CoffeeRepository coffeeRepository,
                           SugarResideRepository sugarResideRepository,
                           @Value("${standard.portion.sugar}") int standardPortionSugar) {
        super(coffeeMakerService, waterResideRepository, coffeeRepository, sugarResideRepository,
                milkResideRepository, standardPortionSugar);
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
