package ru.tyutterin.coffeemaker.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "flushing")
@NoArgsConstructor
public class FlushingCoffeeMaker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coffee_maker_id")
    private CoffeeMaker coffeeMaker;
    @CreationTimestamp
    private LocalDateTime startTime;

    public FlushingCoffeeMaker(CoffeeMaker coffeeMaker) {
        this.coffeeMaker = coffeeMaker;
    }
}
