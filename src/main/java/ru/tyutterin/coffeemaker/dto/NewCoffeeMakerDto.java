package ru.tyutterin.coffeemaker.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.tyutterin.coffeemaker.validation.num.CheckWaterAmount;

import static ru.tyutterin.coffeemaker.validation.Constant.MAX_SIZE_FIRM_MAKER;
import static ru.tyutterin.coffeemaker.validation.Constant.MAX_SIZE_MODEL_MAKER;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@CheckWaterAmount
public class NewCoffeeMakerDto {
    @NotBlank
    @Size(max = MAX_SIZE_FIRM_MAKER)
    private String firm;
    @NotBlank
    @Size(max = MAX_SIZE_MODEL_MAKER)
    private String model;
    @Positive
    private int milkCompartment; //ml
    @Positive
    private int sugarCompartment; //gr
    @Positive
    private int waterCompartment; //ml
    @Positive
    private int orderTiming; //seconds
    @Positive
    private int flushingTiming; //seconds
    @Positive
    private int orderFlushingCount;
    @Positive
    private int amountOfWaterForFlushing;

}
