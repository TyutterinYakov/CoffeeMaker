package ru.tyutterin.coffeemaker.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CoffeeMakerDto {
    private Long id;
    private String firm;
    private String model;
    private int milkCompartment;
    private int milkResidue;
    private int sugarCompartment;
    private int sugarResidue;
    private int waterCompartment;
    private int waterResidue;
    private Integer orderTiming; //seconds
    private Integer flushingTiming; //seconds
    private Integer orderFlushingCount;
}
