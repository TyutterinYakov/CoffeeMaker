package ru.tyutterin.coffeemaker.mapper;

import lombok.experimental.UtilityClass;
import ru.tyutterin.coffeemaker.dto.CoffeeMakerDto;
import ru.tyutterin.coffeemaker.dto.CoffeeMakerInfoDto;
import ru.tyutterin.coffeemaker.dto.NewCoffeeMakerDto;
import ru.tyutterin.coffeemaker.dto.UpdateCoffeeMakerDto;
import ru.tyutterin.coffeemaker.model.entity.CoffeeMaker;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class CoffeeMakerMapper {
    public static CoffeeMaker toModel(NewCoffeeMakerDto newCoffeeMakerDto) {
        return CoffeeMaker.builder()
                .firm(newCoffeeMakerDto.getFirm())
                .flushingTiming(newCoffeeMakerDto.getFlushingTiming())
                .milkCompartment(newCoffeeMakerDto.getMilkCompartment())
                .amountOfWaterForFlushing(newCoffeeMakerDto.getAmountOfWaterForFlushing())
                .model(newCoffeeMakerDto.getModel())
                .sugarCompartment(newCoffeeMakerDto.getSugarCompartment())
                .waterCompartment(newCoffeeMakerDto.getWaterCompartment())
                .coffeeCompartment(newCoffeeMakerDto.getCoffeeCompartment())
                .build();
    }

    public static CoffeeMaker toModel(UpdateCoffeeMakerDto updateCoffeeMakerDto) {
        return CoffeeMaker.builder()
                .model(updateCoffeeMakerDto.getModel())
                .firm(updateCoffeeMakerDto.getFirm())
                .build();
    }

    public static CoffeeMakerDto toDto(CoffeeMaker coffeeMaker) {
        return CoffeeMakerDto.builder()
                .id(coffeeMaker.getId())
                .firm(coffeeMaker.getFirm())
                .milkCompartment(coffeeMaker.getMilkCompartment())
                .flushingTiming(coffeeMaker.getFlushingTiming())
                .sugarCompartment(coffeeMaker.getSugarCompartment())
                .waterCompartment(coffeeMaker.getWaterCompartment())
                .coffeeCompartment(coffeeMaker.getCoffeeCompartment())
                .model(coffeeMaker.getModel())
                .amountOfWaterForFlushing(coffeeMaker.getAmountOfWaterForFlushing())
                .on(coffeeMaker.isOn())
                .build();
    }

    public static CoffeeMakerInfoDto toInfoDto(CoffeeMaker coffeeMaker) {
        return CoffeeMakerInfoDto.builder()
                .id(coffeeMaker.getId())
                .firm(coffeeMaker.getFirm())
                .milkCompartment(coffeeMaker.getMilkCompartment())
                .flushingTiming(coffeeMaker.getFlushingTiming())
                .sugarCompartment(coffeeMaker.getSugarCompartment())
                .waterCompartment(coffeeMaker.getWaterCompartment())
                .coffeeCompartment(coffeeMaker.getCoffeeCompartment())
                .model(coffeeMaker.getModel())
                .milkResidue(coffeeMaker.getMilkResidue())
                .sugarResidue(coffeeMaker.getSugarResidue())
                .waterResidue(coffeeMaker.getWaterResidue())
                .coffeeResidue(coffeeMaker.getCoffeeResidue())
                .amountOfWaterForFlushing(coffeeMaker.getAmountOfWaterForFlushing())
                .on(coffeeMaker.isOn())
                .build();
    }

    public static List<CoffeeMakerDto> toDto(List<CoffeeMaker> coffeeMaker) {
        return coffeeMaker.stream().map(CoffeeMakerMapper::toDto).collect(Collectors.toList());
    }

    public static List<CoffeeMakerInfoDto> toInfoDto(List<CoffeeMaker> coffeeMaker) {
        return coffeeMaker.stream().map(CoffeeMakerMapper::toInfoDto).collect(Collectors.toList());
    }
}
