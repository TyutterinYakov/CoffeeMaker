package ru.tyutterin.coffeemaker.service.impl.validation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.tyutterin.coffeemaker.dto.NewCoffeeDto;
import ru.tyutterin.coffeemaker.model.entity.CoffeeMaker;
import ru.tyutterin.coffeemaker.model.entity.MilkResidue;
import ru.tyutterin.coffeemaker.repository.MilkResideRepository;
import ru.tyutterin.coffeemaker.service.CoffeeValidatorService;


@Service
public class CoffeeValidatorServiceMilk implements CoffeeValidatorService {

    private final MilkResideRepository milkResideRepository;
    private final int percentageMilkOfTheWholePortion;

    public CoffeeValidatorServiceMilk(MilkResideRepository milkResideRepository,
                                      @Value("${standard.portion.milk}") int percentageMilkOfTheWholePortion) {
        this.milkResideRepository = milkResideRepository;
        this.percentageMilkOfTheWholePortion = percentageMilkOfTheWholePortion;
    }

    @Override
    public void checkTheAmount(NewCoffeeDto newCoffee, CoffeeMaker coffeeMaker) {
        Integer portionMilk = newCoffee.getPortionMilk();

        if (portionMilk == null) {
            return;
        }
        //percentage of the whole portion
        int sizePortionMilk = newCoffee.getSizePortion() * (percentageMilkOfTheWholePortion / 100) * portionMilk;

        long milkReside = coffeeMaker.getMilkResidue();

        if (milkReside - sizePortionMilk < 0) {
            throw new RuntimeException("Choose a smaller portion, or replenish the milk");
        }

        milkResideRepository.save(new MilkResidue(coffeeMaker, -sizePortionMilk));
    }
}
