package ru.tyutterin.coffeemaker.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.tyutterin.coffeemaker.dto.CoffeeMakerDto;
import ru.tyutterin.coffeemaker.dto.CoffeeMakerInfoDto;
import ru.tyutterin.coffeemaker.dto.NewCoffeeMakerDto;
import ru.tyutterin.coffeemaker.dto.UpdateCoffeeMakerDto;
import ru.tyutterin.coffeemaker.mapper.CoffeeMakerMapper;
import ru.tyutterin.coffeemaker.model.entity.CoffeeMaker;
import ru.tyutterin.coffeemaker.service.CoffeeMakerService;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/coffeemaker")
@Validated
public class CoffeeMakerController {

    private final CoffeeMakerService coffeeMakerService;

    @PostMapping
    public CoffeeMakerDto add(@Valid @RequestBody NewCoffeeMakerDto newCoffeeMakerDto) {
        CoffeeMaker coffeeMaker = coffeeMakerService.add(CoffeeMakerMapper.toModel(newCoffeeMakerDto));
        return CoffeeMakerMapper.toDto(coffeeMaker);
    }

    @PutMapping("/{coffeeMakerId}")
    public CoffeeMakerDto update(@PathVariable long coffeeMakerId,
                                 @Valid @RequestBody UpdateCoffeeMakerDto updateCoffeeMakerDto) {
        CoffeeMaker coffeeMaker = coffeeMakerService.update(coffeeMakerId, CoffeeMakerMapper.toModel(updateCoffeeMakerDto));
        return CoffeeMakerMapper.toDto(coffeeMaker);
    }

    @PostMapping("/{coffeeMakerId}/on")
    public void on(@PathVariable long coffeeMakerId) {
        coffeeMakerService.on(coffeeMakerId);
    }

    @PostMapping("/{coffeeMakerId}/off")
    public void off(@PathVariable long coffeeMakerId) {
        coffeeMakerService.off(coffeeMakerId);
    }

    @GetMapping
    public List<CoffeeMakerInfoDto> searchAll(@RequestParam(defaultValue = "0") @PositiveOrZero int from,
                                              @RequestParam(defaultValue = "10") @Positive int size) {
        return CoffeeMakerMapper.toInfoDto(coffeeMakerService.search(from, size));

    }

    @GetMapping("/available")
    public List<CoffeeMakerInfoDto> searchOnlyAvailable(@RequestParam(defaultValue = "0") @PositiveOrZero int from,
                                          @RequestParam(defaultValue = "10") @Positive int size) {
        return CoffeeMakerMapper.toInfoDto(coffeeMakerService.searchOnlyAvailable(from, size));

    }

    @PostMapping("/milk")
    public void pourTheMilkFully(@RequestParam long coffeeMakerId) {
        coffeeMakerService.pourTheMilkFully(coffeeMakerId);
    }

    @PostMapping("/flushing")
    public void flushingTheSystem(@RequestParam long coffeeMakerId) {
        coffeeMakerService.flushingTheSystem(coffeeMakerId);
    }

    @PostMapping("/water")
    public void pourTheWaterFully(@RequestParam long coffeeMakerId) {
        coffeeMakerService.pourTheWaterFully(coffeeMakerId);
    }

    @PostMapping("/sugar")
    public void pourTheSugarFully(@RequestParam long coffeeMakerId) {
        coffeeMakerService.pourTheSugarFully(coffeeMakerId);
    }

}
