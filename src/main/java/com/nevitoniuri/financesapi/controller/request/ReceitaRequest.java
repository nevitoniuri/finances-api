package com.nevitoniuri.financesapi.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class ReceitaRequest {
    @NotEmpty
    @NotNull
    private String descricao;

    @DecimalMin(value = "0.01", inclusive = false)
    @NotNull
    @Digits(integer = 10, fraction = 2)
    private BigDecimal valor;

    @PastOrPresent
    @NotNull
    private LocalDate data;
}
