package ru.tyutterin.coffeemaker.model.entity;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Entity
@Table(name = "coffee_makers")
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CoffeeMaker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firm;
    private String model;
    private int milkCompartment; //ml
    private int sugarCompartment; //gr
    private int waterCompartment; //ml
    private int coffeeCompartment; //gr
    private int flushingTiming; //seconds
    @Column(name = "turned_on")
    private boolean on; //true - on | false - off
    private int amountOfWaterForFlushing;
    @Transient
    private int milkResidue;
    @Transient
    private int sugarResidue;
    @Transient
    private int waterResidue;
    @Transient
    private int coffeeResidue;


    public CoffeeMaker(CoffeeMaker coffeeMaker, int milkResidue, int sugarResidue,
                       int waterResidue, int coffeeResidue) {
        this.id = coffeeMaker.id;
        this.firm = coffeeMaker.firm;
        this.model = coffeeMaker.model;
        this.milkCompartment = coffeeMaker.milkCompartment;
        this.sugarCompartment = coffeeMaker.sugarCompartment;
        this.waterCompartment = coffeeMaker.waterCompartment;
        this.coffeeResidue = coffeeResidue;
        this.flushingTiming = coffeeMaker.flushingTiming;
        this.on = coffeeMaker.on;
        this.amountOfWaterForFlushing = coffeeMaker.amountOfWaterForFlushing;
        this.milkResidue = milkResidue;
        this.sugarResidue = sugarResidue;
        this.waterResidue = waterResidue;
        this.coffeeCompartment = coffeeMaker.coffeeCompartment;
    }

}
