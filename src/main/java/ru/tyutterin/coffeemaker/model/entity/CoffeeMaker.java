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
    private long milkResidue;
    @Transient
    private long sugarResidue;
    @Transient
    private long waterResidue;
    @Transient
    private long coffeeResidue;


    public CoffeeMaker(CoffeeMaker coffeeMaker, Long milkResidue, Long sugarResidue,
                       Long waterResidue, Long coffeeResidue) {

        this.id = coffeeMaker.id;
        this.firm = coffeeMaker.firm;
        this.model = coffeeMaker.model;
        this.milkCompartment = coffeeMaker.milkCompartment;
        this.sugarCompartment = coffeeMaker.sugarCompartment;
        this.waterCompartment = coffeeMaker.waterCompartment;
        this.flushingTiming = coffeeMaker.flushingTiming;
        this.on = coffeeMaker.on;
        this.amountOfWaterForFlushing = coffeeMaker.amountOfWaterForFlushing;
        if (milkResidue != null) this.milkResidue = milkResidue;
        if (sugarResidue != null) this.sugarResidue = sugarResidue;
        if (waterResidue != null) this.waterResidue = waterResidue;
        if (coffeeResidue != null) this.coffeeResidue = coffeeResidue;
        this.coffeeCompartment = coffeeMaker.coffeeCompartment;
    }

}
