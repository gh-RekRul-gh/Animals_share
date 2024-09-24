package ru.ruslan.animals.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record OwnerRegisterDto(
        @Pattern(regexp = "^[a-zA-Z0-9_%+-]+[a-zA-Z0-9._%+-]+@[a-zA-Z0-9-]+[a-zA-Z0-9.-]+[a-zA-Z0-9-]+\\.[a-zA-Z]{2,}$",
                message = "Invalid email format")
        @NotNull(message = "Invalid email: Email must not be null")
        @Size(max = 255, message = "Email size must be less than or equal to 255")
        String email,

        @NotEmpty(message = "Invalid password: Password must not be empty")
        @Size(min = 6, max = 100, message = "Password size must be between 6 and 100")
        String password
) {
}
