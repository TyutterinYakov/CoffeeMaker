package ru.tyutterin.coffeemaker.service;

import ru.tyutterin.coffeemaker.model.entity.CoffeeMaker;

import java.util.List;

public interface CoffeeMakerService {

    void flushingTheSystem(long coffeeMakerId);

    CoffeeMaker add(CoffeeMaker model);

    CoffeeMaker update(long coffeeMakerId, CoffeeMaker model);

    List<CoffeeMaker> search(int from, int size);

    void pourTheMilkFully(long coffeeMakerId);

    void pourTheWaterFully(long coffeeMakerId);

    void pourTheSugarFully(long coffeeMakerId);

    List<CoffeeMaker> searchOnlyAvailable(int from, int size);

    void off(long coffeeMakerId);

    void on(long coffeeMakerId);

    void pourTheCoffeeFully(long coffeeMakerId);
}
