package ru.ruslan.animals.utility;

import lombok.experimental.UtilityClass;
import ru.ruslan.animals.enumeration.AnimalType;
import ru.ruslan.animals.exception.WrongAnimalTypeException;

import java.util.Locale;

@UtilityClass
public class AnimalTypeUtil {
    public static final String WRONG_ANIMAL_TYPE_MESSAGE = """
            This animal type is not supported
            Try sending request another request with simplified animal
            For example:
            Use "crane" instead of "japanese crane\"""";

    public AnimalType getAnimalType(String animalTypeString) {
        try {
            return AnimalType.valueOf(animalTypeString.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException exception) {
            throw new WrongAnimalTypeException(WRONG_ANIMAL_TYPE_MESSAGE);
        }
    }
}
