package ru.tyutterin.coffeemaker.controller;


import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tyutterin.coffeemaker.dto.CoffeeDto;
import ru.tyutterin.coffeemaker.dto.NewCoffee;
import ru.tyutterin.coffeemaker.mapper.CoffeeMapper;
import ru.tyutterin.coffeemaker.model.entity.CoffeeType;
import ru.tyutterin.coffeemaker.service.CoffeeService;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/coffee")
public class CoffeeController {

    private final Map<CoffeeType, CoffeeService> coffeeServices;

    public CoffeeController(List<CoffeeService> coffeeServices) {
        this.coffeeServices = coffeeServices.stream().collect(
                Collectors.toMap(CoffeeService::getType, Function.identity()));
    }


    @PostMapping
    public CoffeeDto create(@Valid NewCoffee newCoffee) {
        CoffeeService coffeeService = coffeeServices.get(newCoffee.getCoffeeType());
        if (coffeeService == null) {
            throw new RuntimeException("Неизвестный тип кофе"); //TODO
        }
        return CoffeeMapper.toDto(coffeeService.build(newCoffee));
    }


}
