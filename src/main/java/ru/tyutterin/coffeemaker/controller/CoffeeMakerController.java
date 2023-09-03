package ru.tyutterin.coffeemaker.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.tyutterin.coffeemaker.dto.*;
import ru.tyutterin.coffeemaker.mapper.CoffeeMakerMapper;
import ru.tyutterin.coffeemaker.model.entity.CoffeeMaker;
import ru.tyutterin.coffeemaker.service.CoffeeMakerService;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/coffeemaker")
@Validated
@Tag(name = "Работа с кофеварками",
        description = "API для работы с кофеварками(получение, создание/обновление, пополнение ресурсов)")
public class CoffeeMakerController {

    private final CoffeeMakerService coffeeMakerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Создание кофеварки", description = "Обратите внимание, что объем воды не должен быть " +
            "меньше чем требуемый для промывки системы")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Конфигурация кофеварки", content =
    @Content(mediaType = "application/json", schema = @Schema(implementation = NewCoffeeMakerDto.class)))
    @ApiResponse(responseCode = "201", description = "Кофеварка создана",
            content = @Content(schema = @Schema(implementation = CoffeeMakerDto.class)))
    public CoffeeMakerDto add(@Valid @RequestBody NewCoffeeMakerDto newCoffeeMakerDto) {
        CoffeeMaker coffeeMaker = coffeeMakerService.add(CoffeeMakerMapper.toModel(newCoffeeMakerDto));
        return CoffeeMakerMapper.toDto(coffeeMaker);
    }

    @PutMapping("/{coffeeMakerId}")
    @Operation(summary = "Обновление кофеварки", description = "Передаем тело для обновления кофеварки")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Конфигурация кофеварки", content =
    @Content(mediaType = "application/json", schema = @Schema(implementation = NewCoffeeMakerDto.class)))
    @ApiResponse(responseCode = "200", description = "Кофеварка обновлена",
            content = @Content(schema = @Schema(implementation = CoffeeMakerDto.class)))
    public CoffeeMakerDto update(@PathVariable long coffeeMakerId,
                                 @Valid @RequestBody UpdateCoffeeMakerDto updateCoffeeMakerDto) {
        CoffeeMaker coffeeMaker = coffeeMakerService.update(coffeeMakerId, CoffeeMakerMapper.toModel(updateCoffeeMakerDto));
        return CoffeeMakerMapper.toDto(coffeeMaker);
    }

    @PostMapping("/{coffeeMakerId}/on")
    @Operation(summary = "Включение кофеварки", description = "Кофеварка должна быть выключена")
    @ApiResponse(responseCode = "200", description = "Кофеварка включена", useReturnTypeSchema = true)
    public void on(@PathVariable long coffeeMakerId) {
        coffeeMakerService.on(coffeeMakerId);
    }

    @PostMapping("/{coffeeMakerId}/off")
    @Operation(summary = "Выключение кофеварки", description = "Кофеварка должна быть включена")
    @ApiResponse(responseCode = "200", description = "Кофеварка включена", useReturnTypeSchema = true)
    public void off(@PathVariable long coffeeMakerId) {
        coffeeMakerService.off(coffeeMakerId);
    }

    @GetMapping
    @Operation(summary = "Получение всех кофеварок")
    @Parameter(name = "from", description = "От какой записи получаем")
    @Parameter(name = "size", description = "Сколько записей получаем")
    @ApiResponse(responseCode = "200", description = "Полное описание кофеварок",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = CoffeeMakerInfoDto.class))))
    public List<CoffeeMakerInfoDto> searchAll(@RequestParam(defaultValue = "0") @PositiveOrZero int from,
                                              @RequestParam(defaultValue = "10") @Positive int size) {
        return CoffeeMakerMapper.toInfoDto(coffeeMakerService.search(from, size));

    }

    @GetMapping("/available")
    @Operation(summary = "Получение всех доступных кофеварок")
    @Parameter(name = "from", description = "От какой записи получаем")
    @Parameter(name = "size", description = "Сколько записей получаем")
    @ApiResponse(responseCode = "200", description = "Полное описание кофеварок",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = CoffeeMakerInfoDto.class))))
    public List<CoffeeMakerInfoDto> searchOnlyAvailable(@RequestParam(defaultValue = "0") @PositiveOrZero int from,
                                          @RequestParam(defaultValue = "10") @Positive int size) {
        return CoffeeMakerMapper.toInfoDto(coffeeMakerService.searchOnlyAvailable(from, size));

    }

    @PostMapping("/milk")
    @Operation(summary = "Пополнение молока", description = "Пополнение происходит до целого")
    @Parameter(name = "coffeeMakerId", description = "Идентификатор кофеварки")
    @ApiResponse(responseCode = "200", description = "Молоко пополнено", useReturnTypeSchema = true)
    public void pourTheMilkFully(@RequestParam long coffeeMakerId) {
        coffeeMakerService.pourTheMilkFully(coffeeMakerId);
    }

    @PostMapping("/flushing")
    @Operation(summary = "Промывка системы", description = "Промывка происходит в любом случае")
    @Parameter(name = "coffeeMakerId", description = "Идентификатор кофеварки")
    @ApiResponse(responseCode = "200", description = "Кофеварка промыта", useReturnTypeSchema = true)
    public void flushingTheSystem(@RequestParam long coffeeMakerId) {
        coffeeMakerService.flushingTheSystem(coffeeMakerId);
    }

    @PostMapping("/water")
    @Operation(summary = "Пополнение воды", description = "Пополнение происходит до целого")
    @Parameter(name = "coffeeMakerId", description = "Идентификатор кофеварки")
    @ApiResponse(responseCode = "200", description = "Вода пополнена", useReturnTypeSchema = true)
    public void pourTheWaterFully(@RequestParam long coffeeMakerId) {
        coffeeMakerService.pourTheWaterFully(coffeeMakerId);
    }

    @PostMapping("/sugar")
    @Operation(summary = "Пополнение сахара", description = "Пополнение происходит до целого")
    @Parameter(name = "coffeeMakerId", description = "Идентификатор кофеварки")
    @ApiResponse(responseCode = "200", description = "Сахар пополнен", useReturnTypeSchema = true)
    public void pourTheSugarFully(@RequestParam long coffeeMakerId) {
        coffeeMakerService.pourTheSugarFully(coffeeMakerId);
    }

    @PostMapping("/coffee")
    @Operation(summary = "Пополнение кофе", description = "Пополнение происходит до целого")
    @Parameter(name = "coffeeMakerId", description = "Идентификатор кофеварки")
    @ApiResponse(responseCode = "200", description = "Кофе пополнен", useReturnTypeSchema = true)
    public void pourTheCoffeeFully(@RequestParam long coffeeMakerId) {
        coffeeMakerService.pourTheCoffeeFully(coffeeMakerId);
    }

}
