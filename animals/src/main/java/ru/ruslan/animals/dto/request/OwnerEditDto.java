package ru.ruslan.animals.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record OwnerEditDto(

        @NotEmpty(message = "Invalid name: Name must not be empty")
        @Size(max = 50, message = "Name size must be less than or equal to 50")
        String name,

        @NotEmpty(message = "Invalid surname: Surname must not be empty")
        @Size(max = 50, message = "Surname size must be less than or equal to 50")
        String surname,

        @NotEmpty(message = "Invalid country: Country must not be empty")
        @Size(max = 50, message = "Country size must be less than or equal to 50")
        String country,

        @NotEmpty(message = "Invalid city: City must not be empty")
        @Size(max = 50, message = "City size must be less than or equal to 50")
        String city
) {
}
