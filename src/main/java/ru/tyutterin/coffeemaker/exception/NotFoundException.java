package ru.tyutterin.coffeemaker.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(long id, Class<?> cl) {
        super("Id: " + id + " | Name: " + cl.getName());
    }

}
