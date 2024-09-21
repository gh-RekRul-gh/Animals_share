package ru.ruslan.animals.controller.handler;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.ruslan.animals.dto.response.ApiError;
import ru.ruslan.animals.dto.response.ApiResponse;
import ru.ruslan.animals.exception.AlreadyExistsException;
import ru.ruslan.animals.exception.EntityNotFoundException;
import ru.ruslan.animals.exception.WrongAnimalTypeException;
import ru.ruslan.animals.exception.ZeroEntitiesToGetException;

import java.util.List;

@ControllerAdvice
@Log4j2
public class ApiExceptionHandler {

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Void>> handleAlreadyExistsException(AlreadyExistsException exception) {
        ApiError error = new ApiError(exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ApiResponse.error(error));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        String warnMessage = "Validation failed for with message" + exception.getMessage();
        log.warn(warnMessage);

        List<ApiError> validationErrors = exception.getBindingResult().getFieldErrors()
                .stream().map(fieldError -> new ApiError(fieldError.getDefaultMessage()))
                .toList();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(validationErrors.toArray(new ApiError[0])));
    }

    @ExceptionHandler(WrongAnimalTypeException.class)
    public ResponseEntity<ApiResponse<Void>> handleWrongAnimalTypeException(WrongAnimalTypeException exception) {
        ApiError error = new ApiError(exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(error));
    }

    @ExceptionHandler(ZeroEntitiesToGetException.class)
    public ResponseEntity<ApiResponse<Void>> handleZeroEntitiesToGetException(ZeroEntitiesToGetException exception) {
        ApiError error = new ApiError(exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error(error));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleEntityNotFoundException(EntityNotFoundException exception) {
        ApiError error = new ApiError(exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(error));
    }
}
