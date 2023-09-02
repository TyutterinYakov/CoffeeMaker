package ru.tyutterin.coffeemaker.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
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
    @PositiveOrZero
    private int sugar;
    @Positive
    private Integer portionMilk; //how many times more than the standard
    @Positive
    private int sizePortion = 350; //milk + water or only water(espresso)
}
