package ru.tyutterin.coffeemaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.tyutterin.coffeemaker.model.entity.CoffeeResidue;

public interface CoffeeResidueRepository extends JpaRepository<CoffeeResidue, Long> {
    @Query("SELECT sum(cof.quantity) from CoffeeResidue cof")
    Integer sumOrNullByCoffeeMakerId(long coffeeMakerId);

    default int sumByCoffeeMakerId(long coffeeMakerId) {
        Integer sum = sumOrNullByCoffeeMakerId(coffeeMakerId);
        if (sum != null) {
            return sum;
        }
        return 0;
    }
}
