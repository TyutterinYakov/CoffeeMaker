package ru.tyutterin.coffeemaker.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "flushing")
public class FlushingCoffeeMaker {

    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private CoffeeMaker coffeeMaker;
    private LocalDateTime startTime;

}
