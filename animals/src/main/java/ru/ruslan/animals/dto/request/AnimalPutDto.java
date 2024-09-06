package ru.ruslan.animals.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AnimalPutDto(
        @NotNull(message = "Invalid animal type: Type is null")
        @NotBlank(message = "Invalid animal type: Type is empty")
        String animal,

        @NotNull(message = "Invalid animal name: Name is null")
        @NotBlank(message = "Invalid animal name: Name is empty")
        String name,

        @NotNull(message = "Invalid owner type: Name is null")
        @NotBlank(message = "Invalid owner type: Name is empty")
        String owner,

        @NotNull(message = "Invalid country type: Country is null")
        @NotBlank(message = "Invalid country type: Country is empty")
        String country,

        @NotNull(message = "Invalid city type: City is null")
        @NotBlank(message = "Invalid city type: City is empty")
        String city
) {
}
