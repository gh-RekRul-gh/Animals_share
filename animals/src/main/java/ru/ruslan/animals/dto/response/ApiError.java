package ru.ruslan.animals.dto.response;

import ru.ruslan.animals.exception.ApiException;

public record ApiError(
        String message
) {
    public ApiError(ApiException exception) {
        this(exception.getMessage());
    }
}
