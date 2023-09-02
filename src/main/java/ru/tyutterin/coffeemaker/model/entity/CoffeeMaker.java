package ru.tyutterin.coffeemaker.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "coffee_makers")
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
    private int orderFlushingCount;
    @Column(name = "turned_on")
    private boolean on; //true - on | false - off
    private int amountOfWaterForFlushing;

}
