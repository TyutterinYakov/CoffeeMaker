package ru.tyutterin.coffeemaker.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.tyutterin.coffeemaker.dto.NewCoffee;
import ru.tyutterin.coffeemaker.model.entity.*;
import ru.tyutterin.coffeemaker.repository.*;
import ru.tyutterin.coffeemaker.service.CoffeeMakerService;
import ru.tyutterin.coffeemaker.service.CoffeeService;

@Service
public class CappuccinoService implements CoffeeService {

    private final CoffeeMakerService coffeeMakerService;
    private final WaterResideRepository waterResideRepository;
    private final CoffeeRepository coffeeRepository;
    private final SugarResideRepository sugarResideRepository;

    private final MilkResideRepository milkResideRepository;
    private final int standardPortionSugar; //gr

    public CappuccinoService(CoffeeMakerService coffeeMakerService,
                             WaterResideRepository waterResideRepository,
                             CoffeeRepository coffeeRepository,
                             SugarResideRepository sugarResideRepository,
                             MilkResideRepository milkResideRepository,
                             @Value("${standard.portion.sugar}") int standardPortionSugar) {
        this.coffeeMakerService = coffeeMakerService;
        this.waterResideRepository = waterResideRepository;
        this.coffeeRepository = coffeeRepository;
        this.standardPortionSugar = standardPortionSugar;
        this.sugarResideRepository = sugarResideRepository;
        this.milkResideRepository = milkResideRepository;
    }

    @Override
    public Coffee build(NewCoffee newCoffee) {
        if (newCoffee.getPortionMilk() == null) {
            throw new RuntimeException("Check out the recipe. Cappuccino is made with milk");
        }

        Long coffeeMakerId = newCoffee.getCoffeeMakerId();
        CoffeeMaker coffeeMaker = coffeeMakerService.findByIdAndCheckOn(coffeeMakerId);

        int sizePortionSugar = newCoffee.getSugar() * standardPortionSugar;
        int sizePortionMilk = newCoffee.getPortionMilk();
        int sizePortionWater = newCoffee.getSizePortion() - sizePortionMilk;

        coffeeMakerService.checkTheAmountOfWater(sizePortionWater, coffeeMaker);
        coffeeMakerService.checkTheAmountOfSugar(sizePortionSugar, coffeeMaker);
        coffeeMakerService.checkTheAmountOfMilk(sizePortionSugar, coffeeMaker);

        milkResideRepository.save(new MilkResidue(coffeeMaker, sizePortionMilk));
        waterResideRepository.save(new WaterResidue(coffeeMaker, sizePortionWater));
        sugarResideRepository.save(new SugarResidue(coffeeMaker, sizePortionSugar));
        return coffeeRepository.save(new Coffee(getType(), coffeeMaker));
    }






    @Override
    public CoffeeType getType() {
        return CoffeeType.CAPPUCCINO;
    }




}
