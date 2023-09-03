package ru.tyutterin.coffeemaker.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.tyutterin.coffeemaker.exception.BadRequestException;
import ru.tyutterin.coffeemaker.exception.NotFoundException;
import ru.tyutterin.coffeemaker.model.entity.*;
import ru.tyutterin.coffeemaker.repository.*;
import ru.tyutterin.coffeemaker.service.CoffeeMakerService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CoffeeMakerServiceImpl implements CoffeeMakerService {

    private final CoffeeMakerRepository coffeeMakerRepository;
    private final MilkResideRepository milkResideRepository;
    private final SugarResideRepository sugarResideRepository;
    private final WaterResideRepository waterResideRepository;
    private final FlushingRepository flushingRepository;
    private final CoffeeResidueRepository coffeeResidueRepository;


    /* Проверки:
    * кофемашина существует
    * кофемашина включена
    * достаточно воды для промывки
    */
    @Override
    public void flushingTheSystem(long coffeeMakerId) { //TODO exception
        CoffeeMaker coffeeMaker = findByIdAndCheckOn(coffeeMakerId);
        long waterReside = coffeeMaker.getWaterResidue();
        int amountOfWaterForFlushing = coffeeMaker.getAmountOfWaterForFlushing();

        long amountWaterAfterFlushing = waterReside - amountOfWaterForFlushing;

        if (amountWaterAfterFlushing < 0) {
            throw new BadRequestException("There is not enough water in the coffee machine with id " + coffeeMakerId
                    + " for washing. First you need to top it up");
        }
        waterResideRepository.save(new WaterResidue(coffeeMaker, -amountWaterAfterFlushing));
        flushingRepository.save(new FlushingCoffeeMaker(coffeeMaker));

    }

    @Override
    public CoffeeMaker add(CoffeeMaker coffeeMaker) {
        return coffeeMakerRepository.save(coffeeMaker);
    }

    @Override
    @Transactional
    public CoffeeMaker update(long coffeeMakerId, CoffeeMaker updatedCoffeeMaker) {
        CoffeeMaker coffeeMaker = findByIdOrThrow(coffeeMakerId);

        updateFieldCoffeeMaker(updatedCoffeeMaker, coffeeMaker);

        return coffeeMaker;
    }

    @Override
    public List<CoffeeMaker> search(int from, int size) {
        return coffeeMakerRepository.findAllInfo(page(from, size));
    }

    @Override
    public void pourTheMilkFully(long coffeeMakerId) {
        CoffeeMaker coffeeMaker = findByIdOrThrow(coffeeMakerId);
        int milkReside = milkResideRepository.sumByCoffeeMakerId(coffeeMakerId);
        int milkCompartment = coffeeMaker.getMilkCompartment();
        int shortageToTheWhole = getShortageToTheWhole(coffeeMakerId, milkCompartment, milkReside, "milk");

        milkResideRepository.save(new MilkResidue(coffeeMaker, shortageToTheWhole));
    }

    @Override
    public void pourTheWaterFully(long coffeeMakerId) {
        CoffeeMaker coffeeMaker = findByIdOrThrow(coffeeMakerId);
        int waterReside = waterResideRepository.sumByCoffeeMakerId(coffeeMakerId);
        int waterCompartment = coffeeMaker.getWaterCompartment();
        int shortageToTheWhole = getShortageToTheWhole(coffeeMakerId, waterCompartment, waterReside, "water");

        waterResideRepository.save(new WaterResidue(coffeeMaker, shortageToTheWhole));
    }

    @Override
    public void pourTheSugarFully(long coffeeMakerId) {
        CoffeeMaker coffeeMaker = findByIdOrThrow(coffeeMakerId);
        int sugarReside = sugarResideRepository.sumByCoffeeMakerId(coffeeMakerId);
        int sugarCompartment = coffeeMaker.getSugarCompartment();
        int shortageToTheWhole = getShortageToTheWhole(coffeeMakerId, sugarCompartment, sugarReside, "sugar");

        sugarResideRepository.save(new SugarResidue(coffeeMaker, shortageToTheWhole));
    }

    @Override
    public void pourTheCoffeeFully(long coffeeMakerId) {
        CoffeeMaker coffeeMaker = findByIdOrThrow(coffeeMakerId);
        int coffeeReside = coffeeResidueRepository.sumByCoffeeMakerId(coffeeMakerId);
        int coffeeCompartment = coffeeMaker.getCoffeeCompartment();
        int shortageToTheWhole = getShortageToTheWhole(
                coffeeMakerId, coffeeCompartment, coffeeReside, "coffee");

        coffeeResidueRepository.save(new CoffeeResidue(coffeeMaker, shortageToTheWhole));
    }

    @Override
    public List<CoffeeMaker> searchOnlyAvailable(int from, int size) { //TODO sql
        return coffeeMakerRepository.findAllInfoOnlyAvailable(page(from, size));
    }

    @Override
    @Transactional
    public void off(long coffeeMakerId) {
        CoffeeMaker coffeeMaker = findByIdOrThrow(coffeeMakerId);
        if (!coffeeMaker.isOn()) {
            throw new BadRequestException("Кофеварка уже выключена");
        }
        coffeeMaker.setOn(false);
    }

    @Override
    @Transactional
    public void on(long coffeeMakerId) {
        CoffeeMaker coffeeMaker = findByIdOrThrow(coffeeMakerId);
        if (coffeeMaker.isOn()) {
            throw new BadRequestException("Кофеварка уже включена");
        }
        coffeeMaker.setOn(true);
    }



    private void updateFieldCoffeeMaker(CoffeeMaker updatedCoffeeMaker, CoffeeMaker coffeeMaker) {
        String firm = updatedCoffeeMaker.getFirm();
        if (firm != null) {
            coffeeMaker.setFirm(firm);
        }

        String model = updatedCoffeeMaker.getModel();
        if (model != null) {
            coffeeMaker.setModel(model);
        }
    }


    private static PageRequest page(int from, int size) {
        return PageRequest.of(from > 0 ? from / size : 0, size);
    }

    private CoffeeMaker findByIdOrThrow(long coffeeMakerId) {
        return coffeeMakerRepository.findById(coffeeMakerId).orElseThrow(() ->
                new NotFoundException(coffeeMakerId, CoffeeMaker.class));
    }



    private static int getShortageToTheWhole(long coffeeMakerId, int compartment, int reside, String valueName) {
        if (compartment == reside) {
            throw new BadRequestException("The " + valueName + " tank is overflowing at the coffee machine with id " + coffeeMakerId);
        }
        return compartment - reside;
    }

    private CoffeeMaker findByIdAndCheckOn(long coffeeMakerId) {
        CoffeeMaker coffeeMaker = coffeeMakerRepository.findByIdAndOn(coffeeMakerId);
        if (coffeeMaker == null) {
            throw new NotFoundException(coffeeMakerId, CoffeeMaker.class);
        }
        return coffeeMaker;
    }


}
