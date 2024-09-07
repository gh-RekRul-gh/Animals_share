package ru.ruslan.animals.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record AnimalPutDto(
        @JsonProperty("animal_type")
        @NotEmpty
        @NotNull(message = "Invalid animal type: Type can not be null")
        @NotBlank(message = "Invalid animal type: Type can not be empty")
        String animalType,

        @JsonProperty("animal_name")
        @NotNull(message = "Invalid animal name: Name can not be null")
        @NotBlank(message = "Invalid animal name: Name can not be empty")
        String animalName,

        @JsonProperty("owner_id")
        @NotNull(message = "Invalid ownerId type: OwnerId can not be null")
        Long ownerId
) {
}
