package ru.tyutterin.coffeemaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.tyutterin.coffeemaker.model.entity.WaterResidue;


public interface WaterResideRepository extends JpaRepository<WaterResidue, Long> {

    @Query("SELECT sum(w.quantity) from WaterResidue w")
    int sumByCoffeeMakerId(long coffeeMakerId);
}
