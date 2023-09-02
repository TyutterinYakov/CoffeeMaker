package ru.tyutterin.coffeemaker.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CoffeeMakerInfoDto {

    private Long id;
    private String firm;
    private String model;
    private int milkCompartment;
    private int sugarCompartment;
    private int waterCompartment;
    private Integer flushingTiming; //seconds
    private int milkResidue;
    private int sugarResidue;
    private int waterResidue;
}
