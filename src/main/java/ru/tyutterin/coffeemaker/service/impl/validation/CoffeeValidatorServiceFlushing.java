package ru.tyutterin.coffeemaker.service.impl.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tyutterin.coffeemaker.dto.NewCoffeeDto;
import ru.tyutterin.coffeemaker.exception.BadRequestException;
import ru.tyutterin.coffeemaker.model.entity.CoffeeMaker;
import ru.tyutterin.coffeemaker.repository.FlushingRepository;
import ru.tyutterin.coffeemaker.service.CoffeeValidatorService;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CoffeeValidatorServiceFlushing implements CoffeeValidatorService {

    private final FlushingRepository flushingRepository;

    @Override
    public void checkTheAmount(NewCoffeeDto newCoffee, CoffeeMaker coffeeMaker) {
        int flushingTiming = coffeeMaker.getFlushingTiming();

        boolean flushingWasCarriedOut = flushingRepository.existsByCoffeeMakerAndStartTimeAfter(
                coffeeMaker, LocalDateTime.now().minusSeconds(flushingTiming));

        if (!flushingWasCarriedOut) {
            throw new BadRequestException("It is necessary to first flush the coffee maker id " + coffeeMaker.getId());
        }
    }
}
