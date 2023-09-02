package ru.tyutterin.coffeemaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tyutterin.coffeemaker.model.entity.SugarResidue;

public interface SugarResideRepository extends JpaRepository<SugarResidue, Long> {
}
