package ru.tyutterin.coffeemaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.tyutterin.coffeemaker.model.entity.CoffeeMaker;
import ru.tyutterin.coffeemaker.model.entity.FlushingCoffeeMaker;

import java.time.LocalDateTime;
import java.util.Optional;

public interface FlushingRepository extends JpaRepository<FlushingCoffeeMaker, Long> {


    boolean existsByCoffeeMakerAndStartTimeAfterOrderByStartTime(CoffeeMaker coffeeMaker, LocalDateTime localDateTime);
}
