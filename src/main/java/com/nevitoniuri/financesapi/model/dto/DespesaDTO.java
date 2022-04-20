package com.nevitoniuri.financesapi.model.dto;

import lombok.*;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DespesaDTO {
    private Long id;

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
