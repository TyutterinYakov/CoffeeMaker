package ru.tyutterin.coffeemaker.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.tyutterin.coffeemaker.dto.NewCoffee;
import ru.tyutterin.coffeemaker.model.entity.*;
import ru.tyutterin.coffeemaker.repository.CoffeeMakerRepository;
import ru.tyutterin.coffeemaker.repository.CoffeeRepository;
import ru.tyutterin.coffeemaker.repository.SugarResideRepository;
import ru.tyutterin.coffeemaker.repository.WaterResideRepository;
import ru.tyutterin.coffeemaker.service.CoffeeMakerService;
import ru.tyutterin.coffeemaker.service.CoffeeService;

@Service
public class EspressoService implements CoffeeService {

    private final CoffeeMakerService coffeeMakerService;
    private final WaterResideRepository waterResideRepository;
    private final CoffeeRepository coffeeRepository;
    private final SugarResideRepository sugarResideRepository;
    private final int standardPortionSugar; //gr

    public EspressoService(CoffeeMakerService coffeeMakerService,
                             WaterResideRepository waterResideRepository,
                             CoffeeRepository coffeeRepository,
                             SugarResideRepository sugarResideRepository,
                             @Value("${standard.portion.sugar}") int standardPortionSugar) {
        this.coffeeMakerService = coffeeMakerService;
        this.waterResideRepository = waterResideRepository;
        this.coffeeRepository = coffeeRepository;
        this.standardPortionSugar = standardPortionSugar;
        this.sugarResideRepository = sugarResideRepository;
    }

    @Override
    public Coffee build(NewCoffee newCoffee) { //TODO check sugar
        if (newCoffee.getPortionMilk() != null) {
            throw new RuntimeException("Check out the recipe. Espresso is made without milk");
        }
        Long coffeeMakerId = newCoffee.getCoffeeMakerId();
        CoffeeMaker coffeeMaker = coffeeMakerService.findByIdAndCheckOn(coffeeMakerId);
        int sizePortion = newCoffee.getSizePortion();

        coffeeMakerService.checkTheAmountOfWater(sizePortion, coffeeMaker);
        int sizePortionSugar = newCoffee.getSugar() * standardPortionSugar;

        coffeeMakerService.checkTheAmountOfSugar(sizePortionSugar, coffeeMaker);

        waterResideRepository.save(new WaterResidue(coffeeMaker, sizePortion));
        sugarResideRepository.save(new SugarResidue(coffeeMaker, sizePortionSugar));
        return coffeeRepository.save(new Coffee(getType(), coffeeMaker));
    }







    @Override
    public CoffeeType getType() {
        return CoffeeType.ESPRESSO;
    }
}
