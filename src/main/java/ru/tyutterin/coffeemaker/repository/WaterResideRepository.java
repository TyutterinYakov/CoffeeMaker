package ru.tyutterin.coffeemaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.tyutterin.coffeemaker.model.entity.CoffeeMakerWithWaterResidue;
import ru.tyutterin.coffeemaker.model.entity.WaterResidue;

import java.util.Optional;

public interface WaterResideRepository extends JpaRepository<WaterResidue, Long> {

}
