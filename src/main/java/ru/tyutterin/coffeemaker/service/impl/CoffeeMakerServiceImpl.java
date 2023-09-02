package ru.tyutterin.coffeemaker.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.tyutterin.coffeemaker.exception.NotFoundException;
import ru.tyutterin.coffeemaker.model.entity.CoffeeMaker;
import ru.tyutterin.coffeemaker.repository.CoffeeMakerRepository;
import ru.tyutterin.coffeemaker.repository.MilkResideRepository;
import ru.tyutterin.coffeemaker.repository.SugarResideRepository;
import ru.tyutterin.coffeemaker.repository.WaterResideRepository;
import ru.tyutterin.coffeemaker.service.CoffeeMakerService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoffeeMakerServiceImpl implements CoffeeMakerService {

    private final CoffeeMakerRepository coffeeMakerRepository;
    private final MilkResideRepository milkResideRepository;
    private final SugarResideRepository sugarResideRepository;
    private final WaterResideRepository waterResideRepository;


    @Override
    public void flushingTheSystem(long coffeeMakerId) {
        CoffeeMaker coffeeMaker = findByIdOrThrow(coffeeMakerId);
        if (!coffeeMaker.isOn()) {
            throw new RuntimeException("CoffeeMaking with id " + coffeeMakerId + " is not on");
        }



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
        return coffeeMakerRepository.findAll(page(from, size)).getContent();
    }

    @Override
    public CoffeeMaker pourTheMilkFully(long coffeeMakerId) {


        return null;
    }

    @Override
    public CoffeeMaker pourTheWaterFully(long coffeeMakerId) {
        return null;
    }

    @Override
    public CoffeeMaker pourTheSugarFully(long coffeeMakerId) {
        return null;
    }

    @Override
    public List<CoffeeMaker> searchOnlyAvailable(int from, int size) {
        return null;
    }

    @Override
    @Transactional
    public void off(long coffeeMakerId) {
        CoffeeMaker coffeeMaker = findByIdOrThrow(coffeeMakerId);
        if (!coffeeMaker.isOn()) {
            throw new RuntimeException("Кофеварка уже выключена"); //TODO
        }
        coffeeMaker.setOn(false);
    }

    @Override
    public void on(long coffeeMakerId) {
        CoffeeMaker coffeeMaker = findByIdOrThrow(coffeeMakerId);
        if (coffeeMaker.isOn()) {
            throw new RuntimeException("Кофеварка уже включена"); //TODO
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
}
