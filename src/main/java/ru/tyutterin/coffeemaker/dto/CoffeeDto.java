package ru.tyutterin.coffeemaker.dto;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import ru.tyutterin.coffeemaker.model.entity.CoffeeMaker;
import ru.tyutterin.coffeemaker.model.entity.CoffeeType;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CoffeeDto {
    private CoffeeType coffeeType;
    private CoffeeMakerDto coffeeMaker;
    private LocalDateTime created;
}
