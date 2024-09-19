package ru.ruslan.animals.dto.response;

public record OwnerDto(
        Long id,
        String email,
        String password,
        String name,
        String surname,
        String country,
        String city
) {
}
