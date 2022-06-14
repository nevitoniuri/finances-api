package com.nevitoniuri.financesapi.controller.disassembler;

import com.nevitoniuri.financesapi.model.Despesa;
import com.nevitoniuri.financesapi.model.dto.DespesaDTO;
import com.nevitoniuri.financesapi.util.Disassemblable;
import org.springframework.stereotype.Component;

@Component
public class DespesaDisassembler implements Disassemblable<Despesa, DespesaDTO> {

    @Override
    public DespesaDTO toDTO(Despesa entity) {
        return DespesaDTO.builder()
                .id(entity.getId())
                .descricao(entity.getDescricao())
                .valor(entity.getValor())
                .data(entity.getData())
                .categoria(entity.getCategoria())
                .build();
    }

}
