package ru.tyutterin.coffeemaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tyutterin.coffeemaker.model.entity.CoffeeMaker;

public interface CoffeeMakerRepository extends JpaRepository<CoffeeMaker, Long> {
}
