package com.nevitoniuri.financesapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResumoDoMesDTO {
    private BigDecimal totalReceitas;
    private BigDecimal totalDespesas;
    private BigDecimal saldoFinal;
    private List<CategoriaDTO> gastosPorCategoria;
}
