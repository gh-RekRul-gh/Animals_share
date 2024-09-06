package ru.ruslan.animals.exception;

public class WrongAnimalTypeException extends RuntimeException {
    public WrongAnimalTypeException(String message) {
        super(message);
    }
}
