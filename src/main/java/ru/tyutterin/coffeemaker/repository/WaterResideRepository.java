package ru.tyutterin.coffeemaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.tyutterin.coffeemaker.model.entity.WaterResidue;


public interface WaterResideRepository extends JpaRepository<WaterResidue, Long> {

    @Query("SELECT sum(w.quantity) from WaterResidue w")
    Integer sumOrNullByCoffeeMakerId(long coffeeMakerId);

    default int sumByCoffeeMakerId(long coffeeMakerId) {
        Integer sum = sumOrNullByCoffeeMakerId(coffeeMakerId);
        if (sum != null) {
            return sum;
        }
        return 0;
    }
}
