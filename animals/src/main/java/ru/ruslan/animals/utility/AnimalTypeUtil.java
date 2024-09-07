package ru.ruslan.animals.utility;

import lombok.experimental.UtilityClass;
import ru.ruslan.animals.enumeration.AnimalType;
import ru.ruslan.animals.exception.WrongAnimalTypeException;

import java.util.Locale;

@UtilityClass
public class AnimalTypeUtil {
    public static final String WRONG_ANIMAL_TYPE_MESSAGE = "This animal type is not supported: %s";

    public AnimalType getAnimalType(String animalTypeString) {
        String animalTypeUpper = animalTypeString.toUpperCase(Locale.ROOT);
        try {
            return AnimalType.valueOf(animalTypeUpper);
        } catch (IllegalArgumentException exception) {
            throw new WrongAnimalTypeException(String.format(WRONG_ANIMAL_TYPE_MESSAGE, animalTypeUpper));
        }
    }
}
