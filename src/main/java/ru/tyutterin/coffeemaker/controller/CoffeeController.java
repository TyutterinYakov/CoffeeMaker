package ru.tyutterin.coffeemaker.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.tyutterin.coffeemaker.dto.CoffeeDto;
import ru.tyutterin.coffeemaker.dto.NewCoffee;
import ru.tyutterin.coffeemaker.exception.BadRequestException;
import ru.tyutterin.coffeemaker.mapper.CoffeeMapper;
import ru.tyutterin.coffeemaker.model.entity.CoffeeType;
import ru.tyutterin.coffeemaker.service.CoffeeService;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/coffee/order")
@Tag(name = "Работа с кофе",
        description = "API для работы с кофе")
public class CoffeeController {

    private final Map<CoffeeType, CoffeeService> coffeeServices;

    public CoffeeController(List<CoffeeService> coffeeServices) {
        this.coffeeServices = coffeeServices.stream().collect(
                Collectors.toMap(CoffeeService::getType, Function.identity()));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Заказ кофе", description = "Передаем параметры для создания кофе")
    @Parameter(name = "coffeeMakerId", description = "Идентификатор кофемашины, на которой он будет готовиться")
    @Parameter(name = "coffeeType", description = "Тип кофе, например, CAPPUCCINO, ESPRESSO")
    @Parameter(name = "sugar", description = "Количество порций сахара(одна - 4г)")
    @Parameter(name = "portionMilk", description = "Количество стандартных порций(33% от всего объема)")
    @Parameter(name = "portionCoffee", description = "Количество стандартных порций кофе(16% от всего объема)")
    @Parameter(name = "sizePortion", description = "Размер порции, в мл")
    @ApiResponse(responseCode = "201", description = "Кофе готов",
            content = @Content(schema = @Schema(implementation = CoffeeDto.class)))
    public CoffeeDto create(@Valid NewCoffee newCoffee) {
        CoffeeService coffeeService = coffeeServices.get(newCoffee.getCoffeeType());
        if (coffeeService == null) {
            throw new BadRequestException("Такой кофе пока что отсутствует в нашей системе, " +
                    "попробуйте выбрать другой");
        }
        return CoffeeMapper.toDto(coffeeService.build(newCoffee));
    }


}
