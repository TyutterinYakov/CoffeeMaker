package ru.tyutterin.coffeemaker.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "waters")
public class WaterResidue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private CoffeeMaker coffeeMaker;
    private LocalDateTime fallTime;
    private int quantity;
}
