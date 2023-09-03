package ru.tyutterin.coffeemaker.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Schema(description = "Полная информация о кофеварке")
public class CoffeeMakerInfoDto {

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
    private int milkResidue;
    @Schema(description = "Количество оставшегося сахара")
    private int sugarResidue;
    @Schema(description = "Количество оставшейся воды")
    private int waterResidue;
    @Schema(description = "Количество оставшегося кофе")
    private int coffeeResidue;
    @Schema(description = "Индикатор включения")
    private boolean on;




}
