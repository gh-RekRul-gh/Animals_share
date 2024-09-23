package ru.ruslan.animals.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AnimalPutDto(
        @NotEmpty(message = "Invalid animal type: Type must not be empty")
        @Size(max = 255, message = "AnimalType size must be less than or equal to 255")
        String animalType,

        @NotEmpty(message = "Invalid animal name: Animal name must not be empty")
        @Size(max = 255, message = "AnimalName size must be less than or equal to 255")
        String animalName,

        @NotNull(message = "Invalid ownerId type: OwnerId must not be null")
        long ownerId
) {
}
