package ru.tyutterin.coffeemaker.mapper;

import lombok.experimental.UtilityClass;
import ru.tyutterin.coffeemaker.dto.CoffeeDto;
import ru.tyutterin.coffeemaker.model.entity.Coffee;

@UtilityClass
public class CoffeeMapper {

    public static CoffeeDto toDto(Coffee coffee) {
        return CoffeeDto.builder()
                .coffeeType(coffee.getCoffeeType())
                .coffeeMaker(CoffeeMakerMapper.toDto(coffee.getCoffeeMaker()))
                .build();
    }
}
