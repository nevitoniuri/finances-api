package com.nevitoniuri.financesapi.mapper;

import com.nevitoniuri.financesapi.controller.request.DespesaRequest;
import com.nevitoniuri.financesapi.model.Despesa;
import com.nevitoniuri.financesapi.model.dto.DespesaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DespesaMapper {
    DespesaMapper INSTANCE = Mappers.getMapper(DespesaMapper.class);

    DespesaDTO toDTO(Despesa despesa);

    Despesa toEntity(DespesaRequest despesaRequest);
}
