package ru.tyutterin.coffeemaker.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.tyutterin.coffeemaker.validation.NullOrNotBlank;

import static ru.tyutterin.coffeemaker.validation.Constant.MAX_SIZE_FIRM_MAKER;
import static ru.tyutterin.coffeemaker.validation.Constant.MAX_SIZE_MODEL_MAKER;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCoffeeMakerDto {
    @Size(max = MAX_SIZE_FIRM_MAKER)
    @NullOrNotBlank
    private String firm;
    @Size(max = MAX_SIZE_MODEL_MAKER)
    @NullOrNotBlank
    private String model;
}
