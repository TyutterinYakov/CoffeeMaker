package ru.tyutterin.coffeemaker.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tyutterin.coffeemaker.dto.NewCoffee;
import ru.tyutterin.coffeemaker.model.entity.Coffee;
import ru.tyutterin.coffeemaker.model.entity.CoffeeMaker;
import ru.tyutterin.coffeemaker.model.entity.CoffeeType;
import ru.tyutterin.coffeemaker.model.entity.WaterResidue;
import ru.tyutterin.coffeemaker.repository.CoffeeMakerRepository;
import ru.tyutterin.coffeemaker.repository.CoffeeRepository;
import ru.tyutterin.coffeemaker.repository.WaterResideRepository;
import ru.tyutterin.coffeemaker.service.CoffeeMakerService;
import ru.tyutterin.coffeemaker.service.CoffeeService;

@Service
@RequiredArgsConstructor
public class EspressoService implements CoffeeService {

    private final CoffeeMakerService coffeeMakerService;
    private final WaterResideRepository waterResideRepository;
    private final CoffeeRepository coffeeRepository;

    @Override
    public Coffee build(NewCoffee newCoffee) { //TODO check sugar
        if (newCoffee.getPortionMilk() != null) {
            throw new RuntimeException("Check out the recipe. Espresso is made without milk");
        }
        Long coffeeMakerId = newCoffee.getCoffeeMakerId();
        CoffeeMaker coffeeMaker = coffeeMakerService.findByIdAndCheckOn(coffeeMakerId);
        int sizePortion = newCoffee.getSizePortion();

        coffeeMakerService.checkTheAmountOfWater(sizePortion, coffeeMaker);
        waterResideRepository.save(new WaterResidue(coffeeMaker, sizePortion));

        return coffeeRepository.save(new Coffee(getType(), coffeeMaker));
    }







    @Override
    public CoffeeType getType() {
        return CoffeeType.ESPRESSO;
    }
}
