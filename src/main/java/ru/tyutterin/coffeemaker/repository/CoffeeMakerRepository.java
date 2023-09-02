package ru.tyutterin.coffeemaker.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.tyutterin.coffeemaker.model.entity.CoffeeMaker;

import java.util.List;

public interface CoffeeMakerRepository extends JpaRepository<CoffeeMaker, Long> {


    @Query("select new ru.tyutterin.coffeemaker.model.entity.CoffeeMaker(" +
            "c, " +
            "(SELECT sum(m.quantity) from MilkResidue m where m.coffeeMaker = c), " +
            "(SELECT sum(s.quantity) from SugarResidue s where s.coffeeMaker = c), " +
            "(SELECT sum(w.quantity) from WaterResidue w where w.coffeeMaker = c)" +
            ") from CoffeeMaker c")
    List<CoffeeMaker> findAllInfo(PageRequest pageRequest);

    @Query("select new ru.tyutterin.coffeemaker.model.entity.CoffeeMaker(" +
                "c, " +
                "sum(m), " +
                "sum(s), " +
                "sum(w)" +
            ") from CoffeeMaker c " +
                "left join MilkResidue m ON (m.coffeeMaker = c) " +
                "left join SugarResidue s ON (s.coffeeMaker = c) " +
                "left join WaterResidue w ON (w.coffeeMaker = c) " +
            "where c.on = true")
    List<CoffeeMaker> findAllInfoOnlyAvailable(PageRequest page);
}
