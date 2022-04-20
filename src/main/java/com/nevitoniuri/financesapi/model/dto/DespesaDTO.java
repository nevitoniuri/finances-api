package com.nevitoniuri.financesapi.model.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class DespesaDTO {
    private Long id;
    private String descricao;
    private BigDecimal valor;
    private LocalDate data;

}
