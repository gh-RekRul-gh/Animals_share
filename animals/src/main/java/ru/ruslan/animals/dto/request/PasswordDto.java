package ru.ruslan.animals.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record PasswordDto(
        @NotEmpty(message = "Invalid password: Password must not be empty")
        @Size(min = 6, max = 100, message = "Password size must be between 6 and 100")
        String password
) {
}
