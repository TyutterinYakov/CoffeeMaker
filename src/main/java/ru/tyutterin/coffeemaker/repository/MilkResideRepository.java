package ru.tyutterin.coffeemaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.tyutterin.coffeemaker.model.entity.MilkResidue;

public interface MilkResideRepository extends JpaRepository<MilkResidue, Long> {
    @Query("SELECT sum(m.quantity) from MilkResidue w")
    int sumByCoffeeMakerId(long coffeeMakerId);
}
