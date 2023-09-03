package ru.tyutterin.coffeemaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.tyutterin.coffeemaker.model.entity.MilkResidue;

public interface MilkResideRepository extends JpaRepository<MilkResidue, Long> {
    @Query("SELECT sum(m.quantity) from MilkResidue m")
    Integer sumOrNullByCoffeeMakerId(long coffeeMakerId);


    default int sumByCoffeeMakerId(long coffeeMakerId) {
        Integer sum = sumOrNullByCoffeeMakerId(coffeeMakerId);
        if (sum != null) {
            return sum;
        }
        return 0;
    }
}
