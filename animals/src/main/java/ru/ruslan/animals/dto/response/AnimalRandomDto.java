package ru.ruslan.animals.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.ruslan.animals.enumeration.AnimalType;

public record AnimalRandomDto(
        @JsonProperty("animal") AnimalType animalType,
        @JsonProperty("name") String animalName,
        @JsonProperty("owner") String ownerName,
        String country,
        String city
) {
}
