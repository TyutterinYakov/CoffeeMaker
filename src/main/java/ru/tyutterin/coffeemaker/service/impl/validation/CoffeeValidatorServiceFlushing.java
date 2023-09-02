package ru.tyutterin.coffeemaker.service.impl.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tyutterin.coffeemaker.dto.NewCoffee;
import ru.tyutterin.coffeemaker.model.entity.CoffeeMaker;
import ru.tyutterin.coffeemaker.repository.FlushingRepository;
import ru.tyutterin.coffeemaker.service.CoffeeValidatorService;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CoffeeValidatorServiceFlushing implements CoffeeValidatorService {

    private final FlushingRepository flushingRepository;

    @Override
    public void checkTheAmount(NewCoffee newCoffee, CoffeeMaker coffeeMaker) {
        int flushingTiming = coffeeMaker.getFlushingTiming();

        boolean flushingWasCarriedOut = flushingRepository.existsByCoffeeMakerAndStartTimeAfterOrderByStartTime(
                coffeeMaker, LocalDateTime.now().plusSeconds(flushingTiming));

        if (!flushingWasCarriedOut) {
            throw new RuntimeException("It is necessary to first flush the coffee maker id " + coffeeMaker.getId());
        }
    }
}
