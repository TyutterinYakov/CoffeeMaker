package ru.tyutterin.coffeemaker.service.impl.validation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.tyutterin.coffeemaker.dto.NewCoffee;
import ru.tyutterin.coffeemaker.model.entity.CoffeeMaker;
import ru.tyutterin.coffeemaker.model.entity.SugarResidue;
import ru.tyutterin.coffeemaker.repository.SugarResideRepository;
import ru.tyutterin.coffeemaker.service.CoffeeValidatorService;

@Service
public class CoffeeValidatorServiceSugar implements CoffeeValidatorService {

    private final SugarResideRepository sugarResideRepository;
    private final int standardPortionSugar;

    public CoffeeValidatorServiceSugar(SugarResideRepository sugarResideRepository,
                                       @Value("${standard.portion.sugar}") int standardPortionSugar) {
        this.sugarResideRepository = sugarResideRepository;
        this.standardPortionSugar = standardPortionSugar;
    }

    @Override
    public void checkTheAmount(NewCoffee newCoffee, CoffeeMaker coffeeMaker) {
        int sugarAmount = newCoffee.getSugar() * standardPortionSugar; //number of servings per serving weight
        if (coffeeMaker.getSugarCompartment() < sugarAmount) {
            throw new RuntimeException("Choose a smaller portion, or choose a different coffee maker");
        }

        int sugarReside = coffeeMaker.getSugarResidue();

        if (sugarReside - sugarAmount < 0) {
            throw new RuntimeException("Choose a smaller portion, or replenish the sugar");
        }
        sugarResideRepository.save(new SugarResidue(coffeeMaker, -sugarAmount));
    }
}
