package ru.tyutterin.coffeemaker.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CoffeeMakerDto {
    private Long id;
    private String firm;
    private String model;
    private int milkCompartment;
    private int sugarCompartment;
    private int waterCompartment;
    private Integer orderTiming; //seconds
    private Integer flushingTiming; //seconds
}
