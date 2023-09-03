package ru.tyutterin.coffeemaker.service.impl.validation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.tyutterin.coffeemaker.dto.NewCoffeeDto;
import ru.tyutterin.coffeemaker.exception.BadRequestException;
import ru.tyutterin.coffeemaker.model.entity.CoffeeMaker;
import ru.tyutterin.coffeemaker.model.entity.CoffeeResidue;
import ru.tyutterin.coffeemaker.repository.CoffeeResidueRepository;
import ru.tyutterin.coffeemaker.service.CoffeeValidatorService;


@Service
public class CoffeeValidatorServiceCoffee implements CoffeeValidatorService {

    private final CoffeeResidueRepository coffeeResidueRepository;
    private final int percentCoffeeWeight;

    public CoffeeValidatorServiceCoffee(CoffeeResidueRepository coffeeResidueRepository,
                                        @Value("${standard.portion.coffee}") int percentCoffeeWeight) {
        this.coffeeResidueRepository = coffeeResidueRepository;
        this.percentCoffeeWeight = percentCoffeeWeight;
    }

    @Override
    public void checkTheAmount(NewCoffeeDto newCoffee, CoffeeMaker coffeeMaker) {

        //number of servings per serving weight
        int coffeeAmount = (newCoffee.getSizePortion() / percentCoffeeWeight) * newCoffee.getPortionCoffee();

        if (coffeeMaker.getCoffeeCompartment() < coffeeAmount) {
            throw new RuntimeException("Choose a smaller portion, or choose a different coffee maker");
        }

        long coffeeReside = coffeeMaker.getCoffeeResidue();

        if (coffeeReside - coffeeAmount < 0) {
            throw new BadRequestException("Choose a smaller portion, or replenish the coffee");
        }
        coffeeResidueRepository.save(new CoffeeResidue(coffeeMaker, -coffeeReside));

    }
}
