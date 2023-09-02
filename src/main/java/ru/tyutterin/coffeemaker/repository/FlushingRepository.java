package ru.tyutterin.coffeemaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tyutterin.coffeemaker.model.entity.FlushingCoffeeMaker;

public interface FlushingRepository extends JpaRepository<FlushingCoffeeMaker, Long> {
}
