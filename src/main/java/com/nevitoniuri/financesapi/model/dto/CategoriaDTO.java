package com.nevitoniuri.financesapi.model.dto;

import com.nevitoniuri.financesapi.model.Categoria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDTO {
    private Categoria categoria;
    private BigDecimal total;
}
