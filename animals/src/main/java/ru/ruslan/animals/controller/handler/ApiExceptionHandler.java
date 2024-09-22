package ru.ruslan.animals.controller.handler;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import ru.ruslan.animals.dto.response.ApiError;
import ru.ruslan.animals.dto.response.ApiResponse;
import ru.ruslan.animals.exception.AlreadyExistsException;
import ru.ruslan.animals.exception.EntityNotFoundException;
import ru.ruslan.animals.exception.InvalidAnimalTypeException;
import ru.ruslan.animals.exception.ZeroEntitiesToGetException;

import java.util.List;

@ControllerAdvice
@Log4j2
public class ApiExceptionHandler {

    public static final String LOG_TWO_FIELDS_FORM = "{}: {}";

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Void>> handleAlreadyExistsException(AlreadyExistsException exception) {
        ApiError error = new ApiError(exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ApiResponse.error(error));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleNoHandlerFoundException(NoHandlerFoundException exception) {
        String warnMessage = "Access to not-existing uri";
        log.warn(LOG_TWO_FIELDS_FORM, warnMessage, exception.getMessage());
        ApiError error = new ApiError("Resource not found");
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(error));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponse<Void>> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        String message = "The IDENTIFIER entered is invalid, as it should contain only number";
        log.warn(LOG_TWO_FIELDS_FORM, message, exception.getMessage());
        ApiError error = new ApiError(message);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(error));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<Void>> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        String message = "The JSON that was passed is invalid";
        log.warn(LOG_TWO_FIELDS_FORM, message, exception.getMessage());
        ApiError error = new ApiError(message);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
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

    @ExceptionHandler(InvalidAnimalTypeException.class)
    public ResponseEntity<ApiResponse<Void>> handleInvalidAnimalTypeException(InvalidAnimalTypeException exception) {
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
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(error));
    }
}
