package ru.tyutterin.coffeemaker.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "coffee")
public class Coffee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private CoffeeType coffeeType;
    @ManyToOne
    private CoffeeMaker coffeeMaker;

}
