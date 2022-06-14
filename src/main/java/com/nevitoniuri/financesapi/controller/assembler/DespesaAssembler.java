package com.nevitoniuri.financesapi.controller.assembler;

import com.nevitoniuri.financesapi.controller.request.DespesaRequest;
import com.nevitoniuri.financesapi.model.Despesa;
import com.nevitoniuri.financesapi.util.Assemblable;
import org.springframework.stereotype.Component;

@Component
public class DespesaAssembler implements Assemblable<DespesaRequest, Despesa> {
    @Override
    public Despesa toEntity(DespesaRequest request) {
        var despesa = new Despesa();
        copyToEntity(request, despesa);
        return despesa;
    }

    public void copyToEntity(DespesaRequest request, Despesa entity) {
        entity.setDescricao(request.getDescricao());
        entity.setValor(request.getValor());
        entity.setData(request.getData());
        entity.setCategoria(request.getCategoria());
    }
}
