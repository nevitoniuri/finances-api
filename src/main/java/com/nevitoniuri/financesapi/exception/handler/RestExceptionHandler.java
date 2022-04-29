package com.nevitoniuri.financesapi.exception.handler;

import com.nevitoniuri.financesapi.exception.BadRequestException;
import com.nevitoniuri.financesapi.exception.BadRequestExceptionDetails;
import com.nevitoniuri.financesapi.exception.ExceptionDetails;
import com.nevitoniuri.financesapi.exception.ValidationExceptionDetails;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Log4j2
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetails> handleBadRequestException(BadRequestException e) {
        return new ResponseEntity<>(BadRequestExceptionDetails.builder()
                .title("Bad Request")
                .status(HttpStatus.BAD_REQUEST.value())
                .userMessage(e.getMessage())
                .developerMessage(e.getClass().getName())
                .timestamp(LocalDateTime.now())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining("; "));
        String messages = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining("; "));

        return new ResponseEntity<>(ValidationExceptionDetails.builder()
                .title("Validation Failed")
                .status(HttpStatus.BAD_REQUEST.value())
                .userMessage("Checar os campos: " + fields)
                .field(fields)
                .message(messages)
                .developerMessage(exception.getClass().getName())
                .timestamp(LocalDateTime.now())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .title(ex.getCause().getMessage())
                .status(status.value())
                .userMessage(ex.getMessage())
                .developerMessage(ex.getClass().getName())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(exceptionDetails, headers, status);
    }
}