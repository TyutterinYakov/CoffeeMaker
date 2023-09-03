package ru.tyutterin.coffeemaker.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.tyutterin.coffeemaker.dto.CoffeeDto;
import ru.tyutterin.coffeemaker.dto.NewCoffeeDto;
import ru.tyutterin.coffeemaker.exception.ApiError;
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
@Slf4j
public class CoffeeController {

    private final Map<CoffeeType, CoffeeService> coffeeServices;

    public CoffeeController(List<CoffeeService> coffeeServices) {
        this.coffeeServices = coffeeServices.stream().collect(
                Collectors.toMap(CoffeeService::getType, Function.identity()));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Создание кофе")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Конфигурация кофе", content =
    @Content(mediaType = "application/json", schema = @Schema(implementation = NewCoffeeDto.class)))
    @ApiResponse(responseCode = "201", description = "Кофе готов",
            content = @Content(schema = @Schema(implementation = CoffeeDto.class)))
    @ApiResponse(responseCode = "400", description = "Check out the recipe. Cappuccino is made with milk", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class), examples =
            @ExampleObject(value = "{\"status\":\"BAD_REQUEST\",\"reason\":\"For the requested operation the conditions are not met.\"," +
                    "\"message\":\"Check out the recipe. Cappuccino is made with milk\",\"timestamp\":\"2023-09-03 16:56:19\"}"))
    })
    @ApiResponse(responseCode = "404", description = "Id: 33 | Name: CoffeeMaker", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class), examples =
            @ExampleObject(value = "{\"status\":\"NOT_FOUND\",\"reason\":\"Not found exception.\"," +
                    "\"message\":\"Id: 33 | Name: CoffeeMaker\",\"timestamp\":\"2023-09-03 16:56:19\"}"))
    })
    @ApiResponse(responseCode = "400", description = "Choose a smaller portion, or replenish the coffee", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class), examples =
            @ExampleObject(value = "{\"status\":\"BAD_REQUEST\",\"reason\":\"For the requested operation the conditions are not met.\"," +
                    "\"message\":\"Choose a smaller portion, or replenish the coffee\",\"timestamp\":\"2023-09-03 16:56:19\"}"))
    })
    public CoffeeDto create(@Valid @RequestBody NewCoffeeDto newCoffee, HttpServletRequest request) {
        log.info("{} {} {}", request.getMethod(), request.getContextPath(), newCoffee);
        CoffeeService coffeeService = coffeeServices.get(newCoffee.getCoffeeType());
        if (coffeeService == null) {
            throw new BadRequestException("Такой кофе пока что отсутствует в нашей системе, " +
                    "попробуйте выбрать другой");
        }
        return CoffeeMapper.toDto(coffeeService.build(newCoffee));
    }


}
