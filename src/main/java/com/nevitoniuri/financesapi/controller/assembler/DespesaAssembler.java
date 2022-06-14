package com.nevitoniuri.financesapi.controller.assembler;

import com.nevitoniuri.financesapi.controller.request.DespesaRequest;
import com.nevitoniuri.financesapi.model.Despesa;
import com.nevitoniuri.financesapi.util.Assemblable;
import org.springframework.stereotype.Component;

@Component
public class DespesaAssembler implements Assemblable<DespesaRequest, Despesa> {
    @Override
    public Despesa toEntity(DespesaRequest request) {
        return Despesa.builder()
                .descricao(request.getDescricao())
                .valor(request.getValor())
                .data(request.getData())
                .categoria(request.getCategoria())
                .build();
    }
}
