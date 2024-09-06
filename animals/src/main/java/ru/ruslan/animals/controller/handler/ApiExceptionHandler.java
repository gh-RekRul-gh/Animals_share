package ru.ruslan.animals.controller.handler;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.ruslan.animals.dto.response.ApiError;
import ru.ruslan.animals.dto.response.ApiResponse;
import ru.ruslan.animals.exception.AlreadyExistsException;
import ru.ruslan.animals.exception.WrongAnimalTypeException;
import ru.ruslan.animals.exception.ZeroEntitiesToGetException;

import java.util.stream.Collectors;

@ControllerAdvice
@Log4j2
public class ApiExceptionHandler {

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Void>> handleAlreadyExistsException(AlreadyExistsException exception) {
        log.error(exception.getMessage());
        ApiError error = new ApiError(exception.getMessage(), HttpStatus.CONFLICT.value());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ApiResponse.error(error));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleAlreadyExistsException(MethodArgumentNotValidException exception) {
        log.error(exception.getMessage());

        String validationErrors = exception.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage)
                .collect(Collectors.joining("\n"));
        ApiError error = new ApiError(validationErrors, HttpStatus.BAD_REQUEST.value());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(error));
    }

    @ExceptionHandler(WrongAnimalTypeException.class)
    public ResponseEntity<ApiResponse<Void>> handleAlreadyExistsException(WrongAnimalTypeException exception) {
        log.error(exception.getMessage());
        ApiError error = new ApiError(exception.getMessage(), HttpStatus.BAD_REQUEST.value());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(error));
    }

    @ExceptionHandler(ZeroEntitiesToGetException.class)
    public ResponseEntity<ApiResponse<Void>> handleAlreadyExistsException(ZeroEntitiesToGetException exception) {
        log.error(exception.getMessage());
        ApiError error = new ApiError(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error(error));
    }
}
