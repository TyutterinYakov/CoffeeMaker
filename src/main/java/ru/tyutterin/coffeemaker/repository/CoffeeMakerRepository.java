package ru.tyutterin.coffeemaker.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.tyutterin.coffeemaker.model.entity.CoffeeMaker;

import java.util.List;

public interface CoffeeMakerRepository extends JpaRepository<CoffeeMaker, Long> {



    @Query("select new ru.tyutterin.coffeemaker.model.entity.CoffeeMaker(" +
            "cm, " +
            "coalesce(m.sum, 0), " +
            "coalesce(s.sum, 0), " +
            "coalesce(w.sum, 0), " +
            "coalesce(c.sum, 0)" +
            ") from CoffeeMaker cm " +
            "left join (select sum(quantity) sum, coffeeMaker coffeeMaker " +
                "from WaterResidue group by coffeeMaker) w on w.coffeeMaker.id  = cm.id " +
            "left join (select sum(quantity) sum, coffeeMaker coffeeMaker " +
                "from MilkResidue group by coffeeMaker.id) m on m.coffeeMaker.id  = cm.id " +
            "left join (select sum(quantity) sum, coffeeMaker coffeeMaker " +
                "from CoffeeResidue group by coffeeMaker.id) c on c.coffeeMaker.id  = cm.id " +
            "left join (select sum(quantity) sum, coffeeMaker coffeeMaker " +
                "from SugarResidue group by coffeeMaker.id) s on s.coffeeMaker.id  = cm.id")
    List<CoffeeMaker> findAllInfo(PageRequest pageRequest);

    @Query(value = "select new ru.tyutterin.coffeemaker.model.entity.CoffeeMaker(" +
            "cm, " +
            "coalesce(m.sum, 0), " +
            "coalesce(s.sum, 0), " +
            "coalesce(w.sum, 0), " +
            "coalesce(c.sum, 0)" +
            ") from CoffeeMaker cm " +
            "left join (select sum(quantity) sum, coffeeMaker coffeeMaker " +
            "from WaterResidue group by coffeeMaker) w on w.coffeeMaker.id  = cm.id " +
            "left join (select sum(quantity) sum, coffeeMaker coffeeMaker " +
            "from MilkResidue group by coffeeMaker.id) m on m.coffeeMaker.id  = cm.id " +
            "left join (select sum(quantity) sum, coffeeMaker coffeeMaker " +
            "from CoffeeResidue group by coffeeMaker.id) c on c.coffeeMaker.id  = cm.id " +
            "left join (select sum(quantity) sum, coffeeMaker coffeeMaker " +
            "from SugarResidue group by coffeeMaker.id) s on s.coffeeMaker.id  = cm.id " +
            "where cm.on")
    List<CoffeeMaker> findAllInfoOnlyAvailable(PageRequest page);



    @Query("select new ru.tyutterin.coffeemaker.model.entity.CoffeeMaker(" +
            "c, " +
            "(SELECT sum(m.quantity) from MilkResidue m where m.coffeeMaker = c), " +
            "(SELECT sum(s.quantity) from SugarResidue s where s.coffeeMaker = c), " +
            "(SELECT sum(w.quantity) from WaterResidue w where w.coffeeMaker = c)," +
            "(SELECT sum(cof.quantity) from CoffeeResidue cof where cof.coffeeMaker = c)" +
            ") from CoffeeMaker c where c.on = true and c.id = ?1")
    CoffeeMaker findByIdAndOn(long coffeeMakerId);
}
