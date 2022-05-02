package com.nevitoniuri.financesapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReceitaDTO {
    private Long id;
    private String descricao;
    private BigDecimal valor;
    private LocalDate data;
}
