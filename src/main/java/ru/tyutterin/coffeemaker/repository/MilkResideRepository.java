package ru.tyutterin.coffeemaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tyutterin.coffeemaker.model.entity.MilkResidue;

public interface MilkResideRepository extends JpaRepository<MilkResidue, Long> {
}
