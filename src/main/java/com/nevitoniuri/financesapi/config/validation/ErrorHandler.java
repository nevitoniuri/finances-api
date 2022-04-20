package com.nevitoniuri.financesapi.config.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@RequiredArgsConstructor
public class ErrorHandler {

    private final MessageSource messageSource;

    public List<ErrorDetails> handle(MethodArgumentNotValidException exception) {
        List<ErrorDetails> listaErros = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(fieldError -> {
            String mensagem = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            ErrorDetails errorDetails = new ErrorDetails(fieldError.getField(), mensagem);
            listaErros.add(errorDetails);
        });
        return listaErros;
    }
}
