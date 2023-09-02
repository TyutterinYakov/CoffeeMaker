package ru.tyutterin.coffeemaker.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "coffee")
@NoArgsConstructor
public class Coffee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private CoffeeType coffeeType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coffee_maker_id")
    private CoffeeMaker coffeeMaker;
    private LocalDateTime created;


    public Coffee(CoffeeType coffeeType, CoffeeMaker coffeeMaker) {
        this.coffeeType = coffeeType;
        this.coffeeMaker = coffeeMaker;
    }
}
