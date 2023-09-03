package ru.tyutterin.coffeemaker.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "coffee")
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Coffee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private CoffeeType coffeeType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coffee_maker_id")
    private CoffeeMaker coffeeMaker;
    @CreationTimestamp
    private LocalDateTime created;


    public Coffee(CoffeeType coffeeType, CoffeeMaker coffeeMaker) {
        this.coffeeType = coffeeType;
        this.coffeeMaker = coffeeMaker;
    }
}
