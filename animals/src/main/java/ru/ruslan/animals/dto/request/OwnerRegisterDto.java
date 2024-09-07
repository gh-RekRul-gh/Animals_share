package ru.ruslan.animals.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OwnerRegisterDto(
        @Email(message = "Invalid email")
        String email,

        @NotNull(message = "Invalid animal type: Type is null")
        @NotBlank(message = "Invalid animal type: Type is empty")
        String password
) {
}
