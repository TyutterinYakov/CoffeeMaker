package ru.tyutterin.coffeemaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.tyutterin.coffeemaker.model.entity.SugarResidue;

public interface SugarResideRepository extends JpaRepository<SugarResidue, Long> {

    @Query("SELECT sum(s.quantity) from SugarResidue s")
    Integer sumOrNullByCoffeeMakerId(long coffeeMakerId);


    default int sumByCoffeeMakerId(long coffeeMakerId) {
        Integer sum = sumOrNullByCoffeeMakerId(coffeeMakerId);
        if (sum != null) {
            return sum;
        }
        return 0;
    }
}
