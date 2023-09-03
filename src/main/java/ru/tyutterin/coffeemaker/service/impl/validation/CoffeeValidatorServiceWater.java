package ru.tyutterin.coffeemaker.service.impl.validation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.tyutterin.coffeemaker.dto.NewCoffeeDto;
import ru.tyutterin.coffeemaker.model.entity.CoffeeMaker;
import ru.tyutterin.coffeemaker.model.entity.WaterResidue;
import ru.tyutterin.coffeemaker.repository.WaterResideRepository;
import ru.tyutterin.coffeemaker.service.CoffeeValidatorService;


@Service
public class CoffeeValidatorServiceWater implements CoffeeValidatorService {

    private final WaterResideRepository waterResideRepository;

    private final int percentageMilkOfTheWholePortion;

    public CoffeeValidatorServiceWater(WaterResideRepository waterResideRepository,
                                      @Value("${standard.portion.milk}") int percentageMilkOfTheWholePortion) {
        this.percentageMilkOfTheWholePortion = percentageMilkOfTheWholePortion;
        this.waterResideRepository = waterResideRepository;
    }
    @Override
    public void checkTheAmount(NewCoffeeDto newCoffee, CoffeeMaker coffeeMaker) {
        int sizePortion = newCoffee.getSizePortion();

        Integer portionMilk = newCoffee.getPortionMilk();

        if (portionMilk != null) {
            sizePortion -= (portionMilk * (percentageMilkOfTheWholePortion / 100));
        }

        if (coffeeMaker.getWaterCompartment() < sizePortion) {
            throw new RuntimeException("Choose a smaller portion, or choose a different coffee maker");
        }

        long waterReside = coffeeMaker.getWaterResidue();

        if (waterReside - sizePortion < 0) {
            throw new RuntimeException("Choose a smaller portion, or replenish the water");
        }
        waterResideRepository.save(new WaterResidue(coffeeMaker, -sizePortion));
    }
}
