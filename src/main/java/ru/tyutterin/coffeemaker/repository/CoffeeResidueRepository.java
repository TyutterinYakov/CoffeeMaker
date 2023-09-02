package ru.tyutterin.coffeemaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.tyutterin.coffeemaker.model.entity.CoffeeResidue;

public interface CoffeeResidueRepository extends JpaRepository<CoffeeResidue, Long> {
    @Query("SELECT sum(cof.quantity) from CoffeeResidue cof")
    int sumByCoffeeMakerId(long coffeeMakerId);
}
