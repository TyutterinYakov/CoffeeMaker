package ru.tyutterin.coffeemaker.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.tyutterin.coffeemaker.validation.str.NullOrNotBlank;

import static ru.tyutterin.coffeemaker.validation.Constant.MAX_SIZE_FIRM_MAKER;
import static ru.tyutterin.coffeemaker.validation.Constant.MAX_SIZE_MODEL_MAKER;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Обновление кофеварки")
public class UpdateCoffeeMakerDto {
    @Size(max = MAX_SIZE_FIRM_MAKER)
    @NullOrNotBlank
    @Schema(description = "Фирма кофеварки")
    private String firm;
    @Size(max = MAX_SIZE_MODEL_MAKER)
    @NullOrNotBlank
    @Schema(description = "Модель кофеварки")
    private String model;

}
