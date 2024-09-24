package ru.ruslan.animals.exception;

public class InvalidAnimalTypeException extends RuntimeException {
    public InvalidAnimalTypeException(String message) {
        super(message);
    }
}
