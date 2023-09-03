package ru.tyutterin.coffeemaker.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import ru.tyutterin.coffeemaker.model.entity.CoffeeType;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Schema(description = "Готовый кофе")
public class CoffeeDto {
    @Schema(description = "Тип приготовленного кофе")
    private CoffeeType coffeeType;
    @Schema(description = "Кофеварка, на которой был приготовлен кофе")
    private CoffeeMakerDto coffeeMaker;
    @Schema(description = "Время приготовления кофе")
    private LocalDateTime created;
}
