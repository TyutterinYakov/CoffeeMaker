package ru.tyutterin.coffeemaker.service.impl;

import ru.tyutterin.coffeemaker.dto.NewCoffee;
import ru.tyutterin.coffeemaker.model.entity.*;
import ru.tyutterin.coffeemaker.repository.CoffeeRepository;
import ru.tyutterin.coffeemaker.repository.MilkResideRepository;
import ru.tyutterin.coffeemaker.repository.SugarResideRepository;
import ru.tyutterin.coffeemaker.repository.WaterResideRepository;
import ru.tyutterin.coffeemaker.service.CoffeeMakerService;
import ru.tyutterin.coffeemaker.service.CoffeeService;

public abstract class AbstractCoffeeService implements CoffeeService {

    private final CoffeeMakerService coffeeMakerService;
    private final WaterResideRepository waterResideRepository;
    private final CoffeeRepository coffeeRepository;
    private final SugarResideRepository sugarResideRepository;

    private final MilkResideRepository milkResideRepository;
    private final int standardPortionSugar;

    public AbstractCoffeeService(CoffeeMakerService coffeeMakerService,
                             WaterResideRepository waterResideRepository,
                             CoffeeRepository coffeeRepository,
                             SugarResideRepository sugarResideRepository,
                             MilkResideRepository milkResideRepository,
                             int standardPortionSugar) {
        this.coffeeMakerService = coffeeMakerService;
        this.waterResideRepository = waterResideRepository;
        this.coffeeRepository = coffeeRepository;
        this.standardPortionSugar = standardPortionSugar;
        this.milkResideRepository = milkResideRepository;
        this.sugarResideRepository = sugarResideRepository;
    }


    @Override
    public Coffee build(NewCoffee newCoffee) { //TODO check flushing
        Long coffeeMakerId = newCoffee.getCoffeeMakerId();
        CoffeeMaker coffeeMaker = coffeeMakerService.findByIdAndCheckOn(coffeeMakerId);
        coffeeMakerService.checkTheFlushingRequirement(coffeeMaker);

        int sizePortionSugar = newCoffee.getSugar() * standardPortionSugar;
        Integer sizePortionMilk = newCoffee.getPortionMilk();
        int sizePortionWater = newCoffee.getSizePortion();
        if (sizePortionMilk != null) {
            sizePortionWater -= sizePortionMilk;
            checkNewCoffeeWithMilk(newCoffee, coffeeMaker);
        }
        checkTheAmountOfWater(sizePortionWater, coffeeMaker);
        checkTheAmountOfSugar(sizePortionSugar, coffeeMaker);

        return coffeeRepository.save(new Coffee(newCoffee.getCoffeeType(), coffeeMaker));
    }


    private void checkTheAmountOfWater(int portion, CoffeeMaker coffeeMaker) {
        checkPortion(portion, coffeeMaker.getWaterCompartment());

        int waterReside = waterResideRepository.sumByCoffeeMakerId(coffeeMaker.getId());

        if (waterReside - portion < 0) {
            throw new RuntimeException("Choose a smaller portion, or replenish the water");
        }
        waterResideRepository.save(new WaterResidue(coffeeMaker, -portion));
    }


    private void checkTheAmountOfSugar(int sugar, CoffeeMaker coffeeMaker) {
        checkPortion(coffeeMaker.getSugarCompartment(), sugar);

        int sugarReside = sugarResideRepository.sumByCoffeeMakerId(coffeeMaker.getId());

        if (sugarReside - sugar < 0) {
            throw new RuntimeException("Choose a smaller portion, or replenish the sugar");
        }
        sugarResideRepository.save(new SugarResidue(coffeeMaker, -sugar));
    }


    private void checkNewCoffeeWithMilk(NewCoffee newCoffee, CoffeeMaker coffeeMaker) {
        int sizePortionMilk = newCoffee.getPortionMilk();
        checkTheAmountOfMilk(sizePortionMilk, coffeeMaker);
        milkResideRepository.save(new MilkResidue(coffeeMaker, -sizePortionMilk));
    }


    private static void checkPortion(int portion, int compartmentCoffeeMaker) {
        if (compartmentCoffeeMaker < portion) {
            throw new RuntimeException("Choose a smaller portion, or choose a different coffee maker");
        }
    }

    private void checkTheAmountOfMilk(int sizePortionMilk, CoffeeMaker coffeeMaker) {
        checkPortion(coffeeMaker.getMilkCompartment(), sizePortionMilk);

        int milkReside = milkResideRepository.sumByCoffeeMakerId(coffeeMaker.getId());

        if (milkReside - sizePortionMilk < 0) {
            throw new RuntimeException("Choose a smaller portion, or replenish the milk");
        }
    }
}
