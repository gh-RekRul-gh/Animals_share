package ru.ruslan.animals.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OwnerEditDto(

        @NotNull(message = "Invalid name: Name is null")
        @NotBlank(message = "Invalid name: Name is empty")
        String name,

        @NotNull(message = "Invalid surname: Surname is null")
        @NotBlank(message = "Invalid surname: Surname is empty")
        String surname,

        @NotNull(message = "Invalid country: Country is null")
        @NotBlank(message = "Invalid country: Country is empty")
        String country,

        @NotNull(message = "Invalid city: City is null")
        @NotBlank(message = "Invalid city: City is empty")
        String city
) {
}
