package ru.tyutterin.coffeemaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tyutterin.coffeemaker.model.entity.Coffee;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
