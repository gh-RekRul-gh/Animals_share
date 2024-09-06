package ru.ruslan.animals.dto.response;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {

    protected final int errorCode;

    public ApiException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
