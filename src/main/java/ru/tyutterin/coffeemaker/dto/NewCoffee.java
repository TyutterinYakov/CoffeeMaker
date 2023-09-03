package ru.tyutterin.coffeemaker.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Параметры для создания кофе")
public class NewCoffee {

    @NotNull
    @Schema(description = "Идентификатор кофемашины, на которой он будет готовиться")
    private Long coffeeMakerId;
    @NotNull
    @Schema(description = "Тип кофе, который будет готовиться")
    private CoffeeType coffeeType;
    @PositiveOrZero
    @Schema(description = "Количество порций сахара(одна - 4г)")
    private int sugar;
    @Positive
    @Schema(description = "Количество стандартных порций(33% от всего объема)")
    private Integer portionMilk;
    @Positive
    @Schema(description = "Количество стандартных порций кофе(16% от всего объема)")
    private Integer portionCoffee;
    @Positive
    @Schema(description = "Размер порции, в мл")
    private int sizePortion = 350; //milk + water or only water(espresso)
}
