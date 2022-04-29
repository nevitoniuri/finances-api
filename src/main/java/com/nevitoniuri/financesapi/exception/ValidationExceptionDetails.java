package com.nevitoniuri.financesapi.exception;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.Objects;

@Getter
@SuperBuilder
public class ValidationExceptionDetails extends ExceptionDetails {
    private String field;
    private String message;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ValidationExceptionDetails)) return false;
        if (!super.equals(o)) return false;
        ValidationExceptionDetails that = (ValidationExceptionDetails) o;
        return Objects.equals(getField(), that.getField()) && Objects.equals(getMessage(), that.getMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getField(), getMessage());
    }
}