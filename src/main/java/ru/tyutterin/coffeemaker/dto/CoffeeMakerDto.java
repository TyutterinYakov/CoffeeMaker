package ru.tyutterin.coffeemaker.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Schema(description = "Информация о кофеварке")
public class CoffeeMakerDto {
    private Long id;
    @Schema(description = "Информация о кофеварке")
    private String firm;
    private String model;
    private int milkCompartment;
    private int sugarCompartment;
    private int waterCompartment;
    private Integer orderTiming; //seconds
    private Integer flushingTiming; //seconds
}
