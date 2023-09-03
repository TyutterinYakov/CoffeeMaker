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

    @Schema(description = "Идентификатор кофеварки")
    private Long id;
    @Schema(description = "Фирма кофеварки")
    private String firm;
    @Schema(description = "Модель кофеварки")
    private String model;
    @Schema(description = "Объем отсека для молока у кофеварки")
    private int milkCompartment;
    @Schema(description = "Объем отсека для сахара у кофеварки")
    private int sugarCompartment;
    @Schema(description = "Объем отсека для воды у кофеварки")
    private int waterCompartment;
    @Schema(description = "Объем отсека для кофе у кофеварки")
    private int coffeeCompartment;
    @Schema(description = "Время, через которое требуется промывка")
    private Integer flushingTiming; //seconds
    @Schema(description = "Индикатор включения")
    private boolean on;
}
