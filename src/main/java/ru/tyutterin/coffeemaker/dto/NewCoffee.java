package ru.tyutterin.coffeemaker.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.tyutterin.coffeemaker.model.entity.CoffeeType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewCoffee {

    @NotNull
    private Long coffeeMakerId;
    @NotNull
    private CoffeeType coffeeType;
    @Positive
    private Integer sugar;
    @Positive
    private Integer portionMilk;
    @Positive
    private Integer sizePortion;
}
