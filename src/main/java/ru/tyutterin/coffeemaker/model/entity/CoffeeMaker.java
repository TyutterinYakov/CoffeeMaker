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
    private int orderTiming; //seconds
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


    public CoffeeMaker(CoffeeMaker coffeeMaker, int milkResidue, int sugarResidue, int waterResidue) {
        this.id = coffeeMaker.id;
        this.firm = coffeeMaker.firm;
        this.model = coffeeMaker.model;
        this.milkCompartment = coffeeMaker.milkCompartment;
        this.sugarCompartment = coffeeMaker.sugarCompartment;
        this.waterCompartment = coffeeMaker.waterCompartment;
        this.orderTiming = coffeeMaker.orderTiming;
        this.flushingTiming = coffeeMaker.flushingTiming;
        this.on = coffeeMaker.on;
        this.amountOfWaterForFlushing = coffeeMaker.amountOfWaterForFlushing;
        this.milkResidue = milkResidue;
        this.sugarResidue = sugarResidue;
        this.waterResidue = waterResidue;
    }
}
