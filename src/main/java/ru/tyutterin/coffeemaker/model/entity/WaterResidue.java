package ru.tyutterin.coffeemaker.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "waters")
@NoArgsConstructor
public class WaterResidue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coffee_maker_id")
    private CoffeeMaker coffeeMaker;
    @CreationTimestamp
    private LocalDateTime fallTime;
    private int quantity;

    public WaterResidue(CoffeeMaker coffeeMaker, int quantity) {
        this.coffeeMaker = coffeeMaker;
        this.quantity = quantity;
    }
}
