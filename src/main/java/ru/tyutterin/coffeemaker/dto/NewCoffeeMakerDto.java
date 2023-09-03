package ru.tyutterin.coffeemaker.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;
import ru.tyutterin.coffeemaker.validation.num.CheckWaterAmount;

import static ru.tyutterin.coffeemaker.validation.Constant.MAX_SIZE_FIRM_MAKER;
import static ru.tyutterin.coffeemaker.validation.Constant.MAX_SIZE_MODEL_MAKER;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@CheckWaterAmount
@Schema(description = "Создание кофеварки")
@ToString
public class NewCoffeeMakerDto {

    @Schema(description = "Фирма кофеварки")
    @NotBlank
    @Size(max = MAX_SIZE_FIRM_MAKER)
    private String firm;
    @Schema(description = "Модель кофеварки")
    @NotBlank
    @Size(max = MAX_SIZE_MODEL_MAKER)
    private String model;
    @Schema(description = "Объем отсека для молока у кофеварки")
    @Positive
    private int milkCompartment; //ml
    @Schema(description = "Объем отсека для сахара у кофеварки")
    @Positive
    private int sugarCompartment; //gr
    @Schema(description = "Объем отсека для воды у кофеварки, не должен быть меньше требуемой воды для промывки")
    @Positive
    private int waterCompartment; //ml
    @Positive
    @Schema(description = "Объем отсека для кофе у кофеварки")
    private int coffeeCompartment; //gr
    @Positive
    @Schema(description = "Время, через которое требуется промывка")
    private int flushingTiming; //seconds
    @Positive
    @Schema(description = "Требуемое количество воды для промывки")
    private int amountOfWaterForFlushing;



}
